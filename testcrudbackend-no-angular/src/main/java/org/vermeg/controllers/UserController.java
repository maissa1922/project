package org.vermeg.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.User;
import org.vermeg.repostories.UserRepository;



import antlr.collections.List;



@RestController 
@RequestMapping("/api")  
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
@Autowired
 UserRepository userRepository;

@GetMapping("/users")
public ArrayList<User> getUsers(){
	
	ArrayList<User> users = new ArrayList<>();
	userRepository.findAll().forEach(users::add);

	return users;
	
}

@GetMapping("/users/{id}")
public Optional<User> getUser(@PathVariable Long id) {
	return userRepository.findById(id);
}

@DeleteMapping("/users/{id}")
public  void deleteUser(@PathVariable Long id) {
	 userRepository.deleteById(id);
}

@PutMapping("/users/{id}")
public  User updateUser(User user) {
	return userRepository.save(user);
}

@PostMapping("/users/{id}")
public  User createUser(User user) {
	return userRepository.save(user);
}

}
