package com.douglas.readvista.loan.validators;

import org.springframework.stereotype.Component;

import com.douglas.readvista.entities.Book;
import com.douglas.readvista.services.exceptions.ValidationException;

@Component
public class BookConditionValidator implements ValidatorForBookCondition {
	
	 public void validator(Book book) {
	        if (book.getCondition() == null) {
	            throw new ValidationException("Book condition is not specified!");
	        }
	        switch (book.getCondition()) {
            case GOOD:
                break;
            case DAMAGED:
                throw new ValidationException("Book is damaged and cannot be loaned!");
            case LOST:
                throw new ValidationException("Book is lost and cannot be loaned!");
        }
    }
}
