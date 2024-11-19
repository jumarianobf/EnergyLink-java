package br.com.fiap.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.fiap.gs.entity")
public class GlobalSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalSolutionApplication.class, args);
	}

}
