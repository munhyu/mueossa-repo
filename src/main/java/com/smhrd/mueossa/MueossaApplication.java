package com.smhrd.mueossa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MueossaApplication {

	public static void main(String[] args) {
		// Dotenv dotenv = Dotenv.load();
		// System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
		// System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
		// System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(MueossaApplication.class, args);
	}

}
