package com.douglas.readvista.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.entities.Loan;
import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	public Loan findById(Integer id) {
		Optional<Loan> obj = loanRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id object " + id + " not found!" ));
	}	
}
