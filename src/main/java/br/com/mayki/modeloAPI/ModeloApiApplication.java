package br.com.mayki.modeloAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ModeloApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModeloApiApplication.class, args);
	}

}
