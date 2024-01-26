package com.douglas.readvista.loan.validators;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class LoanDurationValidator {

	private static final long MAX_LOAN_DURATION = 14;
	
	@Autowired
	private LoanRepository loanRepository;
	
	 public LoanDurationValidator(LoanRepository loanRepository) {
	        this.loanRepository = loanRepository;
	    }
	
	public void validator(BookLoanData data) {
		LocalDateTime proposedReturnDate = data.date();
		
		if (proposedReturnDate == null) {
            throw new ValidationException("Return date cannot be null!");
        }
		
        LocalDateTime loanDate = loanRepository.findLoanDateByBookIdAndCustomerId(data.idBook(), data.idCustomer());
        
        if (loanDate == null) {
            throw new ValidationException("Loan not found!");
        }
        
        long daysBetween = ChronoUnit.DAYS.between(loanDate, proposedReturnDate);

        if (daysBetween > MAX_LOAN_DURATION) {
            throw new ValidationException("Loan duration exceeds the maximum allowed!");
        }	
	}
	
}
