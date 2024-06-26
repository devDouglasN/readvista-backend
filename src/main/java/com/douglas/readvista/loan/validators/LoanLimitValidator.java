package com.douglas.readvista.loan.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class LoanLimitValidator implements ValidatorForBookLoans {
	
	private static final int MAX_LOANS_PER_CUSTOMER = 2;
	
	@Autowired
	private LoanRepository repository;

	public void validator(BookLoanData data) {
		
		var totalLoans  = repository.countLoansByCustomerId(data.idCustomer());
		
		if(totalLoans >= MAX_LOANS_PER_CUSTOMER) {
			throw new ValidationException("Limite de empréstimo atingido para o cliente!");
		}	
	}
}
