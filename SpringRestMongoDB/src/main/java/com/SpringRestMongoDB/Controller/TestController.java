package com.SpringRestMongoDB.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.SpringRestMongoDB.WS.Soap;
import com.SpringRestMongoDB.model.Historique;
import com.SpringRestMongoDB.model.Test;

import com.SpringRestMongoDB.repo.TestRepository;
import com.SpringRestMongoDB.service.UserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TestController {
	
	//@Value("${ENDPOINT_ADDRESS_PROPERTY}")
	private String URL="http://localhost:37901/Conversion_Monnaie/Converion_Monnaie?WSDL";
	
	
	//public static final String ENDPOINT_ADDRESS_PROPERTY="http://localhost:8080/Conversion_Monnaie/Converion_Monnaie?WSDL";
	public static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	HistoriqueController controller_hist;
	
	@Autowired
	private UserService userService;
	
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
	
	
	////////////////lancer web service
	
	@PostMapping("/test/lunch")
	public void lunch_ws(@RequestBody Test test) {

		 Soap soap=new Soap() ;
		
		String resultat="";
		SOAPMessage message = null;
		
		 message =  soap.createSOAPMessageFromFile(this.getClass().getResource("SoapTest.xml"));
		 logger.debug( this.getClass().getResource("SoapTest.xml").toString());
		
		 SOAPMessage message_response= soap.submitRequestMessageToSoapWebService(message,URL);
		 
		 long time_spent=soap.spentTime;
	  String  date=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	  
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 try {
				message_response.writeTo(out);
			} catch (SOAPException e) {
				
				logger.error(e.toString());
			} catch (IOException e) {
				
				logger.error(e.toString());
			}
		     String response_string = new String(out.toByteArray());
		     
       if(response_string.equals(test.getResultat_attendu()))
	        resultat="succees";
       else 
	        resultat="echec";
    
       Historique h=new Historique(test.getNom(), test.getURL(), test.getParametre(),test.getParametre(), test.getResultat_attendu(),
   			test.getEmails(), Long.toString(time_spent),date, resultat);
       
       controller_hist.postTests(h);
       
     
		     
	}
	
	
}
