package com.aluracursos.literatura;

import com.aluracursos.literatura.Principal.Principal;
import com.aluracursos.literatura.repositorio.AutorRepository;
import com.aluracursos.literatura.repositorio.Librorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	Librorepository libroRepositorio;
	@Autowired
	AutorRepository autorRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal (libroRepositorio, autorRepositorio);
		principal.opcion();

		Set<Thread>threadSet = Thread.getAllStackTraces().keySet();
		for (Thread t : threadSet){
			System.out.println(t);

		}


	}
}
