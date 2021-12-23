package com.example.restapi.testservices;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.testModels.TransactionModel;
import com.example.restapi.testModels.UserModel;

@Service
@Transactional
public class UserService implements UserInterface{

	private static final String SQL_FIND_BY_USERNAME = "SELECT USER_ID, USERNAME, PASSWORD FROM TEST_USERS WHERE USERNAME = ?";
	private static final String FIND_BY_ID = "SELECT * FROM TEST_USERS WHERE USER_ID = ?";
	private static final String FIND_AMOUNT = "SELECT * FROM TEST_TRANSACTIONS WHERE USER_ID = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	TransactionInterface transactionInterface;

	@Override
	public UserModel validateUser(String username, String password) throws EtAuthException {
		try {
			UserModel user = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new Object[] {username}, userRowMapper);
			if(!BCrypt.checkpw(password, user.getPassword()))
				throw new EtAuthException("Invalid email/password");
			int a = findByUserId(user.getUserId());
			return user;
		} catch (EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid email/password");
		}
	}
	
	@Override
	public UserModel findById(Integer userId) throws EtResourceNotFoundException {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{userId, }, userRowMapper);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Users not found");
		}
	}

	@Override
	public UserModel logout(Integer userId) {
		UserModel user = jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] {userId}, userRowMapper);
		return user;
	}

	@Override
	public Integer findByUserId(Integer userId) {
		try {
			TransactionModel transactionModel = jdbcTemplate.queryForObject(FIND_AMOUNT, new Object[] { userId }, transactionRowMapper);
			return transactionModel.getAmount(); 
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Nothing Message");
		}
	}
	
	private RowMapper<UserModel> userRowMapper = ((rs, rowNum) -> {
		return new UserModel(rs.getInt("USER_ID"),
				rs.getString("USERNAME"),
				rs.getString("PASSWORD"));
	});


	private RowMapper<TransactionModel> transactionRowMapper = ((rs, rowNum) -> {
		return new TransactionModel(rs.getInt("TRANSACTION_ID"),
				rs.getInt("USER_ID"),
				rs.getString("DESCRIPTION"),
				rs.getInt("AMOUNT"));
	});
}
