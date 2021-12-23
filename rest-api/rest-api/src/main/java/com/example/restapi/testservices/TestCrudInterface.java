package com.example.restapi.testservices;

import java.util.List;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.testModels.Test;

public interface TestCrudInterface {

	List<Test> findAll() throws EtResourceNotFoundException;
	
	Test findById(Integer testId) throws EtResourceNotFoundException;
	
	Integer create(String description, String date) throws EtAuthException;
	
	void update(Integer testId, Test test) throws EtBadRequestException;
	
	void remove(Integer testId);
}
