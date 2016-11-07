package com.home.webapps.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.home.webapps.domain.User;
import com.home.webapps.service.UserService;
import com.home.webapps.ui.form.UserForm;

@Controller  
public class LoginController {
	
	@Autowired
	private UserService loginService;

	@RequestMapping(value={"index.html"}, method=RequestMethod.GET)
    public ModelAndView handleIndex() throws Exception 
    {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("user", new UserForm());
	    return mav;			
    }

	@RequestMapping(value={"/logout"}, method=RequestMethod.GET)
    public ModelAndView handleLogout(HttpServletRequest request) throws Exception 
    {
		if (request.getSession().getAttribute("User") != null) {
			request.getSession().removeAttribute("User");
		}
		
		ModelAndView mav = new ModelAndView("loginPage");
		mav.addObject("user", new UserForm());
	    return mav;			
    }

	@RequestMapping(value={"/login"}, method=RequestMethod.GET)
    public ModelAndView handleLogin() throws Exception 
    {
		ModelAndView mav = new ModelAndView("loginPage");
		mav.addObject("user", new UserForm());
	    return mav;			
    }

	@RequestMapping(value={"/login"}, method=RequestMethod.POST)
    public ModelAndView handleRequest(@ModelAttribute("User") UserForm userForm, 
    								  HttpServletRequest request) 
    		throws Exception 
    {
		try {

			//Is anyone logged in
	        if (request.getSession().getAttribute("User") == null) 
	        { 
				//Try to lookup user and go to index page
				User user = loginService.findByUserName(userForm.getUserName(), userForm.getPassword());
				
				if (user != null) {
					request.getSession().setAttribute("User", user);

					UserForm form = new UserForm();
					form.setUserName(user.getUserName());
					
			        ModelAndView mav = new ModelAndView("index");
			        mav.addObject("user", form);
			        return mav;
				}
				else {
					//Not found go back to login page
					ModelAndView mav = new ModelAndView("loginPage");
			        mav.addObject("user", new UserForm());
				    return mav;			
				}				
	        }
	        else {
		        ModelAndView mav = new ModelAndView("index");
		        return mav;
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
}