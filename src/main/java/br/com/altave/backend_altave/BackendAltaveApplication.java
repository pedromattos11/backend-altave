package br.com.altave.backend_altave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendAltaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAltaveApplication.class, args);
	}

}
