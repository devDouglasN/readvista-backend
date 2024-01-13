package com.douglas.readvista.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.dtos.LoanDTO;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.entities.Loan;
import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private BookService bookService;

	@Autowired
	private CustomerService customerService;

	public Loan findById(Integer id) {
		Optional<Loan> obj = loanRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id object " + id + " not found!"));
	}

	public List<Loan> findAll() {
		return loanRepository.findAll();
	}

	public Loan create(LoanDTO objDTO) {
		return loanRepository.save(newLoan(objDTO));
	}

	private Loan newLoan(LoanDTO objDTO) {
		Book book = bookService.findById(objDTO.getBook());
		Customer customer = customerService.findById(objDTO.getCustomer());
		
		Loan loan = new Loan();
		if(objDTO.getId() != null) {
			loan.setId(objDTO.getId());
		}
		loan.setBook(book);
		loan.setCustomer(customer);
		return loan;
	}
}