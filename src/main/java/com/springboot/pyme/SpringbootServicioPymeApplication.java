package com.springboot.pyme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioPymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioPymeApplication.class, args);
	}

}
