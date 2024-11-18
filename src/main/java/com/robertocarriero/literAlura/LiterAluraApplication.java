package com.robertocarriero.literAlura;

import com.robertocarriero.literAlura.repositry.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.robertocarriero.literAlura.principal.Principal;




@SpringBootApplication
public class
LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

        Principal principal = new Principal(repository);
        principal.muestraElMenu();

	}
}
