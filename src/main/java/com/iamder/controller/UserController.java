
package com.iamder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iamder.model.User;
import com.iamder.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping(value = "/test")
	public String testDynamoDB() {
		return "Hai Iamder";
	}

	@PostMapping(value = "/insert")
	public String insertIntoDynamoDB(@RequestBody User user) {
		repository.insertIntoDynamoDB(user);
		return "Successfully inserted into DynamoDB table";
	}

	@GetMapping
	public ResponseEntity<User> getOneStudentDetails(@RequestParam String uuid, @RequestParam String lastName) {
		User user = repository.getOneUserDetails(uuid, lastName);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "{uuid}/{lastName}")
	public void deleteStudentDetails(@PathVariable("uuid") String uuId, @PathVariable("lastName") String lastName) {
		User user = new User();
		user.setUuId(uuId);
		user.setLastName(lastName);
		repository.deleteStudentDetails(user);
	}
}