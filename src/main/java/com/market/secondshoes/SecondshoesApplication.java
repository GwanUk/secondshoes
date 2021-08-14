package com.market.secondshoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SecondshoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondshoesApplication.class, args);
	}
}
