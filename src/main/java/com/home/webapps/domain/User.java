package com.home.webapps.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
@NamedQuery(
      name = "User.findActiveUserByName", 
      query = "select u from User u where u.userName = :name")
})
/*
@NamedNativeQueries({
   @NamedNativeQuery(
            name="User.findActiveUsers",
            query="select u" + 
                 " from Users u" +
                  " where u.active = 'Y'" +
                 " order by u.lastName",
            resultClass = User.class)
})
*/
@Entity
@Table(name = "USER") 
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id //signifies the primary key
    @Column(name = "ID", nullable = false)
    private int id;
    
    @Column(name = "USERNAME", nullable = false,length = 30)
    private String userName;
    
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;

    @Column(name = "FIRSTNAME", length = 30)
    private String firstName;
    
    @Column(name = "LASTNAME", length = 50)
    private String lastName;
    
    private String email;
    
    // By default column name is same as attribute name
    private String role;
    
    // By default column name is same as attribute name
    private String active;
    
    private String locked;
    
    // By default column name is same as attribute name
    private Date createDate;

    // By default column name is same as attribute name
    private Date lastLogin;

    
	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}