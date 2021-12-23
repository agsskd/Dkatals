package com.example.restapi.testservices;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restapi.exceptions.EtAuthException;
import com.example.restapi.exceptions.EtBadRequestException;
import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.testModels.Test;

@Service
@Transactional
public class TestCrudService implements TestCrudInterface {

	private static final String FIND_ALL = "SELECT * FROM TEST";
	private static final String FIND_BY_ID = "SELECT * FROM TEST WHERE TEST_ID = ?";
	private static final String CREATE_TEST = "INSERT INTO TEST (TEST_ID, DESCRIPTION, DATE) VALUES (NEXTVAL('AUTO_INCREMENT_TEST'), ?, ?)";
	private static final String UPDATE_TEST = "UPDATE TEST SET DESCRIPTION = ?, DATE = ? WHERE TEST_ID = ?";
	private static final String DELETE_TEST = "DELETE FROM TEST WHERE TEST_ID = ?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public List<Test> findAll() throws EtResourceNotFoundException {
		return jdbcTemplate.query(FIND_ALL, new Object[] {}, testRowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Test findById(Integer testId) throws EtResourceNotFoundException {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { testId }, testRowMapper);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Data Test not found");
		}
	}

	@Override
	public Integer create(String description, String date) throws EtAuthException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(CREATE_TEST, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, description);
				ps.setString(2, date);
				return ps;
			}, keyHolder);
			return (Integer) keyHolder.getKeys().get("TEST_ID");
		} catch (Exception e) {
			throw new EtAuthException("Data Test Fail to Created");
		}
	}

	@Override
	public void update(Integer testId, Test test) throws EtBadRequestException {
		try {
			jdbcTemplate.update(UPDATE_TEST, new Object[] { test.getDescription(), test.getDate(), testId });
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Data Test Fail to Updated");
		}
	}

	@Override
	public void remove(Integer testId) {
		int count = jdbcTemplate.update(DELETE_TEST, new Object[] { testId });
		if (count <= 0) {
			throw new EtResourceNotFoundException("Data Test is not Found");
		}
	}

	private RowMapper<Test> testRowMapper = ((rs, rowNum) -> {
		return new Test(rs.getInt("TEST_ID"), rs.getString("DESCRIPTION"), rs.getString("DATE"));
	});
}
