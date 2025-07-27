package com.carvalho.demo_park_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoParkApiApplication {

	public static void main(String[] args) {
		loadEnv();
		SpringApplication.run(DemoParkApiApplication.class, args);
	}

	private static void loadEnv() {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach ( entry  -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});
	}
}
