package com.example.restapi.testcontrollers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.testModels.TransactionModel;
import com.example.restapi.testModels.UserModel;
import com.example.restapi.testservices.TransactionInterface;
import com.example.restapi.testservices.UserInterface;

@RestController
@RequestMapping("/api/testing")
public class UserController {

	@Autowired
	UserInterface userInterface;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
		String username = (String) userMap.get("username");
		String password = (String) userMap.get("password");
		UserModel user = userInterface.validateUser(username, password);
		int userId = user.getUserId();
		Integer amount = userInterface.findByUserId(userId);
		Map<String, String> map = new HashMap<>();
		map.put("response", "Hello, " +user.getUsername());
		map.put("message", "Your Balance, " + amount.toString()+".00");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("logout/{userId}")
	public ResponseEntity<Map<String, String>> logoutUser(HttpServletRequest request, @PathVariable("userId") Integer userId){
		UserModel user = userInterface.findById(userId);
		userInterface.logout(userId);
		Map<String, String> map = new HashMap<>();
		map.put("response", "Thanks, "+user.getUsername());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
