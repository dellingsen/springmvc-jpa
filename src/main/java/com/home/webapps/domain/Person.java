package com.home.webapps.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
@NamedQuery(
      name = "Person.findActivePersonByName", 
      query = "select p from Person p where p.lastName = :name")
})
/*
@NamedNativeQueries({
   @NamedNativeQuery(
            name="Person.findActivePersons",
            query="select p" + 
                 " from Person p" +
                  " where p.active = 'Y'" +
                 " order by p.lastName",
            resultClass = Person.class)
})
*/
@Entity
@Table(name = "PERSON") 
public class Person implements Serializable{

	@Id //signifies the primary key
    @Column(name = "PERSON_ID", nullable = false)
    private int personId;
    
    @Column(name = "FIRST_NAME", nullable = false,length = 50)
    private String firstName;
    
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;
    
    // By default column name is same as attribute name
    private String street;
    
    // By default column name is same as attribute name
    private String city;
    
    @Column(name = "ZIP_CODE",nullable = false)  
    // Name of the corresponding database column
    private String zipCode;
    
    // By default column name is same as attribute name
    private String email;

    // By default column name is same as attribute name
    private String phone;

    
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
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

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}    
}