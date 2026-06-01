package com.interviewPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewPlatformApplication {

	public static void main(String[] args) {
		System.out.println("========== DEBUG STARTUP ==========");
		System.out.println("ENV SPRING_DATASOURCE_URL: " + System.getenv("SPRING_DATASOURCE_URL"));
		System.out.println("ENV PORT: " + System.getenv("PORT"));
		System.out.println("===================================");
		SpringApplication.run(InterviewPlatformApplication.class, args);
		System.out.println("Application Started successfully...");
	}

}
