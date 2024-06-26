package com.douglas.readvista.loan.validators;

import org.springframework.stereotype.Component;

import com.douglas.readvista.entities.Book;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class BookConditionValidator implements ValidatorForBookCondition {
	
	 public void validator(Book book) {
		 
	        if (book.getCondition() == null) {
	            throw new ValidationException("A condição do livro não foi especificada!");
	        }
	        
	        switch (book.getCondition()) {
            case GOOD:
                break;
            case DAMAGED:
                throw new ValidationException("O livro está danificado e não pode ser emprestado!");
            case LOST:
                throw new ValidationException("O livro foi perdido e não pode ser emprestado!");
        }
    }
}
