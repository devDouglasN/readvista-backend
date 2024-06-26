package com.douglas.readvista.loan.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class LoanBookValidator implements ValidatorForBookLoans {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	public void validator(BookLoanData data) {
		
		var bookIsActive = bookRepository.existsByIdAndActiveTrue(data.idBook());
		
		if(!bookIsActive) {
			throw new ValidationException("The book is not available!");
		}
		
		var bookWasBorrowed = loanRepository.existsByBookId(data.idBook());
		
		if(bookIsActive && bookWasBorrowed) {
			throw new ValidationException("Este livro já está emprestado!");
		}
	}
}
