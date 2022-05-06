package com.pavan.projects.tambola;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TambolaApplication {
	public static void main(String[] args) {
		try {
			System.out.println(new ClassPathResource("").getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(TambolaApplication.class, args);
	}

}
