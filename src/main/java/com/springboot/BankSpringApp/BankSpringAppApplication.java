package com.springboot.BankSpringApp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class BankSpringAppApplication {

	@Bean
	public OpenAPI swaggerDocOpenApi() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("this is for development");

		Server prodServer = new Server();
		prodServer.setUrl("http://localhost:8080");
		prodServer.setDescription("this is for Production");

		Contact contact = new Contact();
		contact.setName("banking application");
		contact.setEmail("help.bank.in");
		contact.setUrl("https://mvnrepository.com/");

		License license = new License();
		license.setName("2years license");
		license.setUrl("write license provider's url");

		Info info = new Info();
		info.title("Global Bank");
		info.version("1.0");
		info.contact(contact);
		info.description("designed for banking app");
		info.termsOfService("pass url");
		info.license(license);

		OpenAPI openAPI = new OpenAPI();
		openAPI.info(info);
		openAPI.servers(Arrays.asList(devServer, prodServer));

		return openAPI;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankSpringAppApplication.class, args);
	}

}
