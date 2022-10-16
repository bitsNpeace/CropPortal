package com.trainingapps.cropdeal.userMicroService;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableEurekaClient
public class UserMicroServiceApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(UserMicroServiceApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {

		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.trainingapps.cropdeal")).build().apiInfo(apiDetails());

	}

	private ApiInfo apiDetails() {
		return new ApiInfo("User API", "User API for Crop Deal", "1.0", "Free to use",
				new springfox.documentation.service.Contact("Prakhar Pathak", "localhost:8082",
						"test@gmail.com"),
				"API Licence", "https://cropdeal.com", Collections.emptyList());

	}
//	private ApiKey apiKey() {
//		return new ApiKey("Token_Access","Authorization",SecurityScheme.In.HEADER.name);
//	}
}
