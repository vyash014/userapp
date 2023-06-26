package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user")
public class User 
{	
//	UserID-AutoGenerate
//
//	UserRole-Customer
//
//	Username-fullname
//
//	Email- emailid@email.com
//
//	Password-password@123
//
//	What was your first pet? - Parrot
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String secretquestion;
	private String role;
}
