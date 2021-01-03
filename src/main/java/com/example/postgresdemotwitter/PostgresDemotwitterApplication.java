package com.example.postgresdemotwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.ComponentScan; 



@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages={"com.example"})
public class PostgresDemotwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgresDemotwitterApplication.class, args);
	}

}
