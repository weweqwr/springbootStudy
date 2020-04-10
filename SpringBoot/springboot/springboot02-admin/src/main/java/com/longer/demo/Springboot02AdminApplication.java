package com.longer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAutoConfiguration
@EnableAdminServer
public class Springboot02AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02AdminApplication.class, args);
	}

}
