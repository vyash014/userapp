package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;


@CrossOrigin 
@RestController
@RequestMapping("api/v1")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository uRepo;
	
		
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers()
	{
		List<User> userlist = userService.getAllUsers();
		
		if(userlist!=null)
		{
			return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
		}
		return new ResponseEntity<String>("userlist is empty", HttpStatus.NO_CONTENT);
	}
	
//	@PutMapping("/updateUser")
//	public ResponseEntity<?> updateUser(@RequestBody User user)
//	{
//		if(userService.updateUser(user))
//		{
//			return new ResponseEntity<String>("User got updated successfully",HttpStatus.OK);
//		}
//		
//		return new ResponseEntity<String>("User updation failed",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
