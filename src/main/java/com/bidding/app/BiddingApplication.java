package com.bidding.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Main Class for bidding app
 * @author ARUN P P
 */
@EnableSwagger2
@SpringBootApplication
public class BiddingApplication {

	/**
	 * Main method for bidding app
	 * @param args
	 */
	public static void main( String[] args )
	{
		//Starts the Spring Boot app
		SpringApplication.run(BiddingApplication.class, args);
	}

	/**
	 * Bean to initialize Swagger
	 *
	 * @return swagger bean
	 */
	@Bean
	public Docket newsApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(regex("/rest.*")).build();
	}

	/**
	 * Method to set api information for Swagger
	 *
	 * @return api info
	 */
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("REST Services").description("Rest services for project Bidding App")
				.version("2.0").build();
	}
}
