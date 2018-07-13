


package org.vermeg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vermeg.entities.User;
import org.vermeg.repostories.UserRepository;


@SpringBootApplication
public class CrudBackendApp implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudBackendApp.class, args);
	}								
	@Override
	public void run(String... args) throws Exception {
		

		
	
	}

}
