package com.example.restapi.testservices;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.testModels.UserModel;

public interface UserInterface {

	UserModel validateUser(String username, String password) throws EtAuthException;

	UserModel findById(Integer userId) throws EtResourceNotFoundException;
	
	UserModel logout(Integer userId);
	
	Integer findByUserId(Integer userId) throws EtResourceNotFoundException;
}
