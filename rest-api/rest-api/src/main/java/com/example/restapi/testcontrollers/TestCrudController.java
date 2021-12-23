package com.example.restapi.testcontrollers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.models.Category;
import com.example.restapi.testModels.Test;
import com.example.restapi.testservices.TestCrudInterface;

@RestController
@RequestMapping("/api/test")
public class TestCrudController {

	@Autowired
	TestCrudInterface testCrudInterface;
	
	@GetMapping("")
	public ResponseEntity<List<Test>> getAllDataTest(HttpServletRequest request){
		List<Test> dataTest = testCrudInterface.findAll();
		return new ResponseEntity<>(dataTest, HttpStatus.OK);
	}
	
	@GetMapping("/{testId}")
	public ResponseEntity<Test> getDataTestById(HttpServletRequest request, @PathVariable("testId") Integer testId){
		Test dataTest = testCrudInterface.findById(testId);
		return new ResponseEntity<>(dataTest, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Test> addDataTest(HttpServletRequest request, @RequestBody Map<String, Object> testMap){
		String description = (String) testMap.get("description");
		String date = (String) testMap.get("date");
//		Date ys = new Date(0);
//	    SimpleDateFormat s=new SimpleDateFormat("ddMMyyyy");
//		String date = s.format(ys).toString();
		int testId = testCrudInterface.create(description, date);
		Test dataTest = testCrudInterface.findById(testId);
		return new ResponseEntity<>(dataTest, HttpStatus.CREATED);
	}
	
	@PutMapping("/{testId}")
	public ResponseEntity<Map<String, Boolean>> updateDataTest(HttpServletRequest request, @PathVariable("testId") Integer testId, @RequestBody Test test){
		testCrudInterface.update(testId, test);
		Map<String, Boolean> map = new HashMap<>();
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/{testId}")
	public ResponseEntity<Map<String, Boolean>> deleteDataTest(HttpServletRequest request, @PathVariable("testId") Integer testId){
		testCrudInterface.remove(testId);
		Map<String, Boolean> map = new HashMap<>();
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
