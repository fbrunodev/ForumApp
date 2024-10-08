package com.demo.forumapp.models;

import java.sql.Date;

import org.apache.arrow.vector.holders.TimeStampMicroHolder;

import com.google.cloud.Timestamp;

public class UserModel {
	
	private String name;
	private String email;
	private String phone;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public UserModel() {
		super();
	}
	
	public UserModel( String name, String email, String phone, Timestamp createdAt, Timestamp updatedAt) {
		
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
}
