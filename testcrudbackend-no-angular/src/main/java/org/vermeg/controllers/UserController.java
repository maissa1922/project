package org.vermeg.controllers;

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



@RestController  // This means that this class is a Controller
@RequestMapping("/api")  //This means URL's start with /api (after Application path)


@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
@Autowired
 UserRepository userRepository;


@CrossOrigin
@GetMapping("/users")
public List getUsers(){
	return (List) userRepository.findAll();
}
@GetMapping("/users/{id}")
public Optional<User> getUser(@PathVariable Long id) {
	return userRepository.findById(id);
}

@DeleteMapping("/users/{id}")
public  void deleteUser(@PathVariable Long id) {
	 userRepository.deleteById(id);
}

@PutMapping("/users")
public  User updateUser(User user) {
	return userRepository.save(user);
}

@PostMapping("/users")
public  User createUser(User user) {
	return userRepository.save(user);
}

}
