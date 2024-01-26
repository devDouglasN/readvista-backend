package com.douglas.readvista.loan.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.readvista.repositories.CustomerRepository;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class CustomerAvailabilityValidator {
	
	@Autowired
	private CustomerRepository repository;
	
	public void validator (BookLoanData data) {
		
		if(data.idCustomer() == null) {
			return;
		}
		
		var customerIsEnabled = repository.existsByIdAndActiveTrue(data.idCustomer());
		
		if(!customerIsEnabled) {
			throw new ValidationException("Customer not found!") ;
		}
	}
	
}
