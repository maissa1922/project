package com.SpringRestMongoDB.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.soap.SOAPMessage;

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

import com.SpringRestMongoDB.model.Historique;
import com.SpringRestMongoDB.model.Test;
import com.SpringRestMongoDB.repo.HistoriqueRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class HistoriqueController {
	@Autowired
	HistoriqueRepository repository;
 
	@GetMapping("/historique")
	public List<Historique> getAllTests() {
		
		List<Historique> tests = new ArrayList<>();
		List<Historique> tests_non_nuls = new ArrayList<>();
		
		repository.findAll().forEach(tests::add);
		for(Historique h:tests)

    	
		{   if(h.getResultat()!=null && h.getDate()!=null &&  String.valueOf(h.getTemps_rep_reel())!= null ) 
    	tests_non_nuls.add(h);
	   }
    	
		return tests_non_nuls;
	
	}
 

	@PostMapping("/historique/create")
	public Historique postTests(@RequestBody Historique test) {
 
		
		Historique _test = repository.save(new Historique(test.getNom(), test.getURL(),test.getParametre(),test.getTemps_rep(),test.getResultat_attendu(),test.getEmails(),test.getTemps_rep_reel(),test.getDate(),test.getResultat()));
		return _test;
	
	}



 


}
