package com.douglas.readvista.loan.validators;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class LoanDurationValidator implements ValidatorForBookLoans {

	private static final long MAX_LOAN_DURATION = 14;
	
	public void validator(BookLoanData data) {
		LocalDateTime proposedReturnDate = data.returnDate();
		
		if (proposedReturnDate == null) {
            throw new ValidationException("Return date cannot be null!");
        }
		
		LocalDateTime loanDate = data.date();
        
        if (loanDate == null) {
            throw new ValidationException("Loan not found!");
        }
        
        long daysBetween = ChronoUnit.DAYS.between(loanDate, proposedReturnDate);

        if (daysBetween > MAX_LOAN_DURATION) {
            throw new ValidationException("A duração do empréstimo excede o máximo permitido!");
        }	
	}
	
}
