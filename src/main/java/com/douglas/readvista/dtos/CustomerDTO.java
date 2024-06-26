package com.douglas.readvista.dtos;

import jakarta.validation.constraints.NotNull;

public record CustomerDTO(

		Integer id,

		@NotNull(message = "Field NAME is required") 
		String name,

		@NotNull(message = "Field CPF is required") 
		String cpf,

		@NotNull(message = "Field EMAIL is required") 
		String email,

		@NotNull(message = "Field PASSWORD is required") 
		String password,	
		
		Integer libraryId
		
		) {}