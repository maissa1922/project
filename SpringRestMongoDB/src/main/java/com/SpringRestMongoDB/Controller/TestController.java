package com.SpringRestMongoDB.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.SpringRestMongoDB.model.Test;

import com.SpringRestMongoDB.repo.TestRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TestController {
	@Autowired
	TestRepository repository;
 
	@GetMapping("/test")
	public List<Test> getAllTests() {
	
		List<Test> tests = new ArrayList<>();
		repository.findAll().forEach(tests::add);
 
		return tests;
	}
 

	@PostMapping("/test/create")
	public Test postTests(@RequestBody Test test) {
 
		Test _test = repository.save(new Test(test.getNom(), test.getURL(),test.getParametre(),test.getTemps_rep(),test.getResultat_attendu(),test.getEmails()));
		return _test;
	
	}
 
	@DeleteMapping("/test/delete")
	public ResponseEntity<String> deleteAllTests() {
 
		repository.deleteAll();
 
		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}
 
	@GetMapping("test/url/{url}")
	public List<Test> findByAge(@PathVariable String url) {
 
		List<Test> tests = repository.findAll();
		return tests;
	}
 
	@PutMapping("/test/{id}")
	public ResponseEntity<Test> updatetest(@PathVariable("id") String id, @RequestBody Test test) {
		System.out.println("Update Test with ID = " + id + "...");
 
		Optional<Test> testData = repository.findById(id);
 
		if (testData.isPresent()) {
			Test _test = testData.get();
			_test.setNom(_test.getNom());
			_test.setURL(_test.getURL());
			_test.setParametre(_test.getParametre());
			_test.setTemps_rep(_test.getTemps_rep());
			_test.setEmails(_test.getEmails());
			return new ResponseEntity<>(repository.save(_test), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
