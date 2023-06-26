package com.example.demo;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.example.demo.Controller.UserController;
//import com.example.demo.Model.User;
//import com.example.demo.Service.UserServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@AutoConfigureMockMvc
//@SpringBootTest
public class UserControllerTest 
{
//	@Mock
//	private UserServiceImpl userService;
//	
//	@InjectMocks
//	private UserController userC;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	
//	@BeforeEach
//	public void init()
//	{
//		MockitoAnnotations.openMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(userC).build();
//	}
//
//	List<User> userList = new ArrayList<User>();
//	
//	@Test
//	public void getAllUsersSuccess() throws Exception
//	{
//		User user = new User();
//		//user.setId(101);
//		user.setUsername("Test1");
//		user.setEmail("test1@gmail.com");
//		user.setPassword("test1@123");
//		user.setRole("admin");
//		user.setSecretquestion("Testt1");
//		
//		userList.add(user);
//		when(userService.getAllUsers()).thenReturn(userList);
//		
//		List<User> uList = userService.getAllUsers();
//		assertEquals(userList, uList);
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllUsers").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//		
//	}
//	
//	@Test
//	public void getAllUsersFailure() throws Exception
//	{
//		userList.clear();
//		when(userService.getAllUsers()).thenReturn(userList);
//		
//		assertEquals(0,userList.size());
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("/auth/v1/user/all").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isNotFound());
//		
//	}
//	
//	@Test
//	public void addUserSuccess() throws Exception
//	{
//		User user = new User();
//		user.setId(101);
//		user.setUsername("Test2");
//		user.setEmail("test2@gmail.com");
//		user.setPassword("test2@123");
//		user.setRole("consumer");
//		user.setSecretquestion("Testt2");
//		
//		userList.add(user);
//		System.out.println(user);
//		when(userService.addUser(any())).thenReturn(user);
//		assertEquals(1,userList.size());
//		
//	}
//	
//	@Test
//	public void addUserFailure() throws Exception
//	{
//		
//		when(userService.addUser(any())).thenReturn(null);
//		
//		User u1 = userService.addUser(null);
//		assertNull(u1);
//
//		
//	}

}