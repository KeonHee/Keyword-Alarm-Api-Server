package com.landvibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KeywordAlarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeywordAlarmApplication.class, args);
		System.out.println("Spring Boot를 시작합니다.");
	}
}
