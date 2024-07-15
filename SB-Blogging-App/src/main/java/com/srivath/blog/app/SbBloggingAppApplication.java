package com.srivath.blog.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SbBloggingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbBloggingAppApplication.class, args);
	}

	/*
	 * Note: ModelMapper library used for conversion of Entity into DTO and vice
	 * versa
	 */
	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}

}
