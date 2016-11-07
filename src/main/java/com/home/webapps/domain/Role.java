package com.home.webapps.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id //signifies the primary key
    @Column(name = "ID", nullable = false)
    private int id;
    
    @Column(name = "ROLENAME", nullable = false,length = 30)
    private String roleName;
    
    private String permissions;    
    
	public int getId() {
		return id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}