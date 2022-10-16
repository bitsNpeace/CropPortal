package com.trainingapps.cropdeal.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {SpringApplication.run(OrderServiceApplication.class, args);

	}
	@Bean

	public Docket swaggerConfiguration() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/order/*"))
				.apis(RequestHandlerSelectors.basePackage("com.trainingapps.cropdeal"))
				.build()
				.apiInfo(apiDetails());

	}
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Order API",
				"Order API for Crop Deal",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Maheep Chauhan","https://cropdeal.com","maheep99chauhan@gmail.com"),
				"API Licence",
				"https://cropdeal.com",
				Collections.emptyList());

	}

}
