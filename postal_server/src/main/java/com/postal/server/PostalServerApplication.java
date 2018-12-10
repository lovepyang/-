package com.postal.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class PostalServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostalServerApplication.class, args);
	}
}
