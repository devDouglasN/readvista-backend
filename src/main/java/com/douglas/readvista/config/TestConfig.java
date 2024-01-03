package com.douglas.readvista.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.douglas.readvista.servives.LibrarySystem;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private LibrarySystem librarySystem;
	
	@PostConstruct
	public void testLibrary() {
		 this.librarySystem.startSystem();
	}
}
