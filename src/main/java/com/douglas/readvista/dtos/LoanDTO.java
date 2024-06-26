package com.douglas.readvista.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record LoanDTO(
		
    Integer id,
    
    @JsonFormat(pattern = "dd/MM/yyyy") 
    Date loanDate,
    
    @JsonFormat(pattern = "dd/MM/yyyy") 
    Date returnDate,
    
    @NotNull(message = "Field BOOK is required") 
    Integer book,
    
    @NotNull(message = "Field CUSTOMER is required") 
    Integer customer,
     
    String bookName,
    String customerName
) {
    
}
