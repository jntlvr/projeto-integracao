package com.exemplo.modulo_integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.exemplo.modulo_integrador", "com.exemplo.modulo_orm", "com.exemplo.modulo_odm"})
public class ModuloIntegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloIntegradorApplication.class, args);
	}

}