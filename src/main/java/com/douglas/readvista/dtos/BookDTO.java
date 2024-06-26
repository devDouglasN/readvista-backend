package com.douglas.readvista.dtos;

import com.douglas.readvista.enums.BookCondition;
import com.douglas.readvista.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record BookDTO(

		Integer id, 
		String title, 
		String author, 
		String yearOfPublication,

		@Enumerated(EnumType.STRING) 
		Status status,

		@Enumerated(EnumType.STRING) 
		BookCondition bookCondition

) {

}
