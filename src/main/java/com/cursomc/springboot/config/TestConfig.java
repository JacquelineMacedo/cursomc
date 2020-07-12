package com.cursomc.springboot.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursomc.springboot.services.DBService;
import com.cursomc.springboot.services.EmailService;
import com.cursomc.springboot.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}