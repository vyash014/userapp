package com.example.demo.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.UserNameAlreadyExists;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin
@RestController
@RequestMapping("auth/v1")
public class AuthenticationController 
{
	private Map<String, String> mapObj = new HashMap<String, String>();
	private Map<String, String> mapObj1 = new HashMap<String, String>();
	private int id;
	private String username;
	private String password;
	private String email;
	private String secretquestion;
	private String role;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws UserNameAlreadyExists
	{
		if(userService.addUser(user)!=null)
	{
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
		return new ResponseEntity<String>("user registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PutMapping("/updatePass")
	public ResponseEntity<?> updatepass(@RequestBody User user) 
	{	
		if(userService.updateUser(user))
		{
			return new ResponseEntity<String>("User got updated successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("User updation failed",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestParam String username ,@RequestParam String password) 
	{	
		try {
		User userexists = userService.getUser(username, password);		
		String jwtToken = generateToken(username, password);
		if(userexists !=null && jwtToken !=null)
		{	
			mapObj1.put("jwtToken", jwtToken);
			mapObj1.put("username",userexists.getUsername());
			mapObj1.put("password",userexists.getPassword());
			mapObj1.put("email",userexists.getEmail());
			mapObj1.put("secretquestion",userexists.getSecretquestion());
			mapObj1.put("role",userexists.getRole());
			
			return new ResponseEntity<>(mapObj1, HttpStatus.OK);
		}
		}
		catch(Exception e)
		{
			mapObj.put("Message", "User not logged in");
			mapObj.put("Token:", null);
			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("User record does not exist",HttpStatus.NO_CONTENT);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	
//	@PostMapping("/login")
//	public ResponseEntity<?> logiUser(@RequestParam String username ,@RequestParam String password) 
//	{	
//		try {
//		User userexists = userService.getUser(username, password);		
//		String jwtToken = generateToken(username, password);
//		if(userexists !=null && jwtToken !=null)
//		{	
//			mapObj1.put("jwtToken", jwtToken);
//			mapObj1.put("username",userexists.getUsername());
//			mapObj1.put("password",userexists.getPassword());
//			mapObj1.put("email",userexists.getEmail());
//			mapObj1.put("secretquestion",userexists.getSecretquestion());
//			mapObj1.put("role",userexists.getRole());
//			
//			return new ResponseEntity<>(mapObj1, HttpStatus.OK);
//		}
//		}
//		catch(Exception e)
//		{
//			mapObj.put("Message", "User not logged in");
//			mapObj.put("Token:", null);
//			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
//		}
//		return new ResponseEntity<String>("User record does not exist",HttpStatus.NO_CONTENT);
//	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> logiUser(@RequestBody User user)
	{	
		
		try {
			User userexists = userService.getUser(user.getUsername(), user.getPassword());		
			String jwtToken = generateToken(user.getUsername(), user.getPassword());
			if(userexists !=null && jwtToken !=null)
			{	
				mapObj1.put("jwtToken", jwtToken);
				mapObj1.put("username",userexists.getUsername());
				mapObj1.put("password",userexists.getPassword());
				mapObj1.put("email",userexists.getEmail());
				mapObj1.put("secretquestion",userexists.getSecretquestion());
				mapObj1.put("role",userexists.getRole());
				
				return new ResponseEntity<>(mapObj1, HttpStatus.OK);
			}
			}
	
//		try
//		{
//			String jwtToken = generateToken(user.getUsername(), user.getPassword());
//			mapObj.put("Message", "User successfully logged in");
//			mapObj.put("Token:", jwtToken);
//			
//		}
		catch(Exception e)
		{
			mapObj.put("Message", "User not logged in");
			mapObj.put("Token:", null);
			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(mapObj, HttpStatus.OK);
	}
	
	//method to generate token inside login API
		public String generateToken(String username, String password) throws ServletException, Exception
		{
			String jwtToken;
			if(username ==null || password ==null)
			{
				throw new ServletException("Please enter valid credentials");
			}
			
			boolean flag = userService.loginUser(username, password);
			
			if(!flag)
			{
				throw new ServletException("Invalid credentials");
			}
			
			else
			{
				jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
							.setExpiration(new Date(System.currentTimeMillis()+300000))
							.signWith(SignatureAlgorithm.HS256, "secret key").compact();
							
			}
			
			return jwtToken;
			
		}
	
		@GetMapping("/user/all")
		public ResponseEntity<?> getAllUsers()
		{
			List<User> userList = userService.getAllUsers();
			if(userList.isEmpty())
			{
				return new ResponseEntity<String>("User list is empty", HttpStatus.NOT_FOUND);
			}
			else
				return new ResponseEntity<>(userList, HttpStatus.OK);
		}
	
	
}















