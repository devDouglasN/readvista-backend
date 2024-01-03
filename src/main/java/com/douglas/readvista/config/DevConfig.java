package com.douglas.readvista.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.douglas.readvista.servives.LibrarySystem;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private LibrarySystem system;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

	@Bean
	public boolean startLibrary() {

		if (value.equals("create")) {
			this.system.startSystem();
		}
		return false;
	}
}
