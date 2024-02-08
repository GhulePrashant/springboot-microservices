package com.prashantghule.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
			title = "Department Service REST APIs",
			description = "Department Service REST APIs - Create, Get",
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
				description = "Department Service Doc"
		)
)
@SpringBootApplication
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}

/**
 Run below command to start zipkin as a docker container

 docker run --rm -it --name zipkin -p 9411:9411 openzipkin/zipkin

 Zipkin
 http://127.0.0.1:9411/
 */