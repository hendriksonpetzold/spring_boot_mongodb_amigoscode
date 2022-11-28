package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(StudentRepository repository){
		return args -> {
			String email = "hendrikson_14@hotmail.com";
			Address address = new Address(
					"BR",
					"TO",
					"3554126"
			);
			Student student = new Student(
					"Hendrikson",
					"Petzold",
					email,
					Gender.MALE,
					address,
					List.of("computer", "Games"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
			repository.findStudentByEmail(email).ifPresentOrElse(student1 -> {
				System.out.println("already exists");
			}, ()-> {
				System.out.println("Insert Student");
				repository.insert(student);
			});
		};

	}
}
