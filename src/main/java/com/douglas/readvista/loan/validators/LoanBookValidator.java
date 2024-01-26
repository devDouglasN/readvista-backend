package com.douglas.readvista.loan.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class LoanBookValidator {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	public void validator(BookLoanData data) {
		
		var bookEstaAtivo = bookRepository.existsByIdAndActiveTrue(data.idBook());
		
		if(!bookEstaAtivo) {
			throw new ValidationException("The book is not available!");
		}
		
		var livroFoiEmprestado = loanRepository.existsByBookIdAndCustomerId(data.idBook(), data.idCustomer());
		
		if(bookEstaAtivo && livroFoiEmprestado) {
			throw new ValidationException("The book is already on loan!");
		}
	}
	
}
