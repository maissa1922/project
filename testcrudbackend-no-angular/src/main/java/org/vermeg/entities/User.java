package org.vermeg.entities;
import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "User")
public class User {
	@Id
private Long id;
private String fname;
private String lname;


public User(String fname, String lname) {
	
	this.fname = fname;
	this.lname = lname;
}
public User() {
	
}

@Override
public String toString() {
	return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + "]";
}



public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

}