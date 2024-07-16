package com.frauscher.assignment.VacationService;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class VacationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationServiceApplication.class, args);
		log.info("Vacation Service Has Started...");
	}

}
