package com.example.restapi.testcontrollers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.testModels.Test;
import com.example.restapi.testModels.TransactionModel;
import com.example.restapi.testservices.TransactionInterface;

@RestController
@RequestMapping("/api/testing")
public class TransactionController {

	@Autowired
	TransactionInterface transactionInterface;
	
	@PutMapping("/deposit/{userId}")
	public ResponseEntity<Map<String, String>> updateDataTest(HttpServletRequest request, @PathVariable("userId") Integer userId, @RequestBody TransactionModel transactionModel){
		transactionInterface.deposit(userId, transactionModel);
		Map<String, String> map = new HashMap<>();
		map.put("response", "Your Balance, " +transactionModel.getAmount()+".00");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
