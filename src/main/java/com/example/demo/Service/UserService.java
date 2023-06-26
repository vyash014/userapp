package com.example.demo.Service;

import java.util.List;

import com.example.demo.Exception.UserNameAlreadyExists;
import com.example.demo.Model.User;

public interface UserService 
{
	public User addUser(User user) throws UserNameAlreadyExists;// user registration
	
	public boolean loginUser(String username, String password);// login
	
	public List<User> getAllUsers();// will be visible only if you are logged in
	
	public boolean updatePass(String username, String secretquestion);
	
	public boolean updateUser(User user);
	
	public User getUser(String username, String password);

}
