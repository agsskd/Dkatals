package com.example.restapi.testservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.testModels.TransactionModel;

@Service
@Transactional
public class TransactionService implements TransactionInterface {

	private static final String DEPOSIT_TEST = "UPDATE TEST_TRANSACTIONS SET DESCRIPTION = ?, AMOUNT = ? WHERE USER_ID = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void deposit(Integer userId, TransactionModel transactionModel) throws EtBadRequestException {
		try {
			jdbcTemplate.update(DEPOSIT_TEST, new Object[] { transactionModel.getDescription(), transactionModel.getAmount(), userId });
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Data Test Fail to Updated");
		}
	}
	
}
