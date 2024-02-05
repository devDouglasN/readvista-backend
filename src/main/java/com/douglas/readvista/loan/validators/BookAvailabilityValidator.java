package com.douglas.readvista.loan.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.readvista.dtos.BookLoanData;
import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class BookAvailabilityValidator implements ValidatorForBookLoans {
	
	@Autowired
	private BookRepository repository;
	
	public void validator(BookLoanData data) {
		
		var idBook = repository.existsByIdAndActiveTrue(data.idBook());
		
		if(!idBook) {
			throw new ValidationException("Book not available!");
		}
	}
	
}
