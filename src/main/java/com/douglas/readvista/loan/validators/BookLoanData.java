package com.douglas.readvista.loan.validators;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record BookLoanData(
		
		@NotNull
		Integer idBook,
		
		@NotNull
		Integer idCustomer,
		
		@NotNull
		@Future
		LocalDateTime date
		) {

}
