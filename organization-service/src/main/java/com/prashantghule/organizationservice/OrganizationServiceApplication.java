package com.prashantghule.organizationservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Organization Service REST APIs",
				description = "Organization Service REST APIs - Create, Get",
				contact = @Contact(
						name = "Prashant Ghule",
						email = "ghuleprashant3097@gmail.com",
						url = "https://github.com/GhulePrashant"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/GhulePrashant"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Organization Service Doc"
		)
)
@SpringBootApplication
public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
