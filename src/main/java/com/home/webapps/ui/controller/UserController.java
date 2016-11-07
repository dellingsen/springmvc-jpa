package com.home.webapps.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.webapps.domain.User;
import com.home.webapps.service.UserService;
import com.home.webapps.service.WHSSendMail;
import com.home.webapps.ui.form.UserForm;


@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/userService/findId/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User findUserById(@PathVariable("id") long userId) {
		return userService.findUserById(userId);
	}
	 
	@RequestMapping(value = "/userService/findName/{name}", method = RequestMethod.GET)
	public @ResponseBody String findUserByName(@PathVariable("name") String name) {
		User user = userService.findByUserName(name, null);
		if (user != null) {
			WHSSendMail.sendMail();
			return user.getFirstName() + " " + user.getLastName();
		}
		else {
			return "User by name: " + name + " could not be found.";
		}
	}

	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute UserForm userForm, BindingResult result) 
		throws Exception 
	{
		//Password is hashed before lookup in DAO
    	User user = userService.findByUserName(userForm.getUserName(), userForm.getPassword());
		

		if (user == null) {
			//Hash password now so the model is ready to be persisted
	    	String hashedPassword = passwordEncoder.encodePassword(userForm.getPassword(), userForm.getUserName());
			user = new User();
			user.setUserName(userForm.getUserName());
			user.setPassword(hashedPassword);
			user.setFirstName(userForm.getFirstName());
			user.setLastName(userForm.getLastName());
			user.setEmail(userForm.getEmail());
			user.setRole("User");
			user.setActive("Y");
			user.setLocked("N");
			
			userService.save(user);
		}
		else {
			//user already exists
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(value="/createUser", method=RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request) 
		throws Exception 
	{
			ModelAndView mav = new ModelAndView("newuser");
			mav.addObject("user", new UserForm());
		    return mav;			
	}

	@RequestMapping(value = "/userService/delete/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") long userId) throws Exception {
		User user = userService.findUserById(userId);
		userService.delete(user);
		return "OK";
	}
	
}
