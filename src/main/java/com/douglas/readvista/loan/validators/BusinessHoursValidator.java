package com.douglas.readvista.loan.validators;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class BusinessHoursValidator implements ValidatorForBookLoans {

	public void validator (BookLoanData data) {
		var date = data.date();
		var sunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var saturday = date.getDayOfWeek().equals(DayOfWeek.SATURDAY);
		var libraryOpening = date.getHour() < 9;
		var libraryClosure = date.getHour() > 18;	
		
		if(sunday || saturday || libraryOpening || libraryClosure) {
			throw new ValidationException("Library outside opening hours!");
		}
	}
}
