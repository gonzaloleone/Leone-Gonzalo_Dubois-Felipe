package com.backend.proyectoClinica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoClinicaApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(ProyectoClinicaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProyectoClinicaApplication.class, args);
		LOGGER.info("Clase13Application is now running...");
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
