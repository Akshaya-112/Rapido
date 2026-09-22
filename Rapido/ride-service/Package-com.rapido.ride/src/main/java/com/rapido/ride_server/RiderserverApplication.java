package com.rapido.ride_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RiderserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiderserverApplication.class, args);
		System.out.println("Rider Service is running on port 8081");
		
		
	}
}
