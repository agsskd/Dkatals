package com.example.restapi.testservices;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.testModels.TransactionModel;

public interface TransactionInterface {

	void deposit(Integer userId, TransactionModel transactionModel) throws EtAuthException;
	
}
