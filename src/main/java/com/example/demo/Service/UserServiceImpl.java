package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.UserNameAlreadyExists;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepo;
	
	
	@KafkaListener(topics = "movieapp", groupId = "mygroup")
	public void consumeFromTicketsTopic(String message){
		System.out.println("Consumer message: "+message);
	}
	
	
	@Override
	public User addUser(User user) throws UserNameAlreadyExists  {
		
		String newUser = user.getUsername();
		User opUser = userRepo.getUserByUname(newUser);
		if(opUser==null)
		{
			return userRepo.saveAndFlush(user);
			
		}
		else {
			throw new UserNameAlreadyExists();
		}
	}

	@Override
	public boolean loginUser(String username, String password) {
		
		User user1 = userRepo.validateUser(username, password);
		System.out.println("User: "+ user1.getUsername());
		if(user1!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
	
		List<User> userList = userRepo.findAll();
		
		if(userList!=null & userList.size() >0)
		{
			return userList;
		}
		else
			return null;
	}

	@Override
	public boolean updatePass(String username, String secretquestion) {
		// TODO Auto-generated method stub
		User user1 = userRepo.forgotpass(username, secretquestion);
		if(user1 != null) {
			updateUser(user1);
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) 
	{
		// TODO Auto-generated method stub	
		String uname = user.getUsername();
		String squestion = user.getSecretquestion();
			if(uname!=null && squestion!=null)
			{
				User foundUser = userRepo.forgotpass(uname, squestion);
				System.out.println(foundUser);
				if(foundUser != null) 
				{	
					foundUser.setPassword(user.getPassword());
					userRepo.saveAndFlush(foundUser);
					return true;
				}	
				else{
					return false;
					}
			}
			return false;
	}

	@Override
	public User getUser(String username, String password) {
		User user2 = userRepo.validateUser(username, password);
		System.out.println("User: "+ user2.getUsername());
		if(user2!=null)
		{
			return user2;
		}
		return null;
		
	}
}













