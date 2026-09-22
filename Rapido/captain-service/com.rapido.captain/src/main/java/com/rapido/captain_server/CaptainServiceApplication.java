package com.rapido.captain_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaptainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaptainServiceApplication.class, args);
		System.out.println("Captain Service is running on port 8082");
	}

}
