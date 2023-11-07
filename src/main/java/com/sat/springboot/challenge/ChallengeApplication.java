package com.sat.springboot.challenge;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Bean
	public OpenAPI openApiInformation() {
		Server localServer =
				new Server().url("http://localhost:8080").description("Localhost Server URL");
		Contact contact = new Contact().email("satyas@gmail.com").name("Satya S");
		Info info = new Info().contact(contact).description("Challenge - Vehicle API")
				.summary("Challenge - Vehicle API")
				.title("Challenge - Vehicle API").version("V1.0.0")
				.license(new License().name("Apache 2.0").url("http://springdoc.org"));

		return new OpenAPI().info(info).addServersItem(localServer);
	}
}
