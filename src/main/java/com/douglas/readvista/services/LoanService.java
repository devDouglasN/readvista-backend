package com.douglas.readvista.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.douglas.readvista.loan.validators.BookLoanData;
import com.douglas.readvista.loan.validators.ValidatorForBookCondition;
import com.douglas.readvista.loan.validators.ValidatorForBookLoans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.dtos.LoanDTO;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.entities.Loan;
import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.repositories.CustomerRepository;
import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private List<ValidatorForBookLoans> validators = new ArrayList<>();

	@Autowired
	private List<ValidatorForBookCondition> bookConditionValidators = new ArrayList<>();

	public Loan findById(Integer id) {
		Optional<Loan> obj = loanRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id object " + id + " not found!"));
	}

	public List<Loan> findAll() {
		return loanRepository.findAll();
	}

	public Loan update(Integer id, @Valid LoanDTO objDTO) {
		objDTO.setId(id);
		Loan oldObj = findById(id);
		oldObj = newLoan(objDTO);
		return loanRepository.save(oldObj);
	}

	private Loan newLoan(LoanDTO objDTO) {	
		Book book = bookService.findById(objDTO.getBook());
		Customer customer = customerService.findById(objDTO.getCustomer());

		Loan loan = new Loan();
		if (objDTO.getId() != null) {
			loan.setId(objDTO.getId());
		}
		
		loan.setBook(book);
		loan.setCustomer(customer);
		return loan;
	}
	
	public Loan loan (BookLoanData data) {
		if(!customerRepository.existsById(data.idCustomer())){
			throw new ObjectNotFoundException("The provided customer ID was not found!");
		}
		if(!bookRepository.existsById(data.idBook())) {
			throw new ObjectNotFoundException("The provided book ID was not found!");
		}

		var book = bookRepository.findById(data.idBook()).get();
		System.out.println(book.getTitle());

		validators.forEach(v -> v.validator(data));
		bookConditionValidators.forEach(v -> v.validator(book));

		var customer = customerRepository.findById(data.idCustomer()).get();
		var loan = new Loan(null, book, customer);
		LocalDateTime loanDate = data.date() != null ? data.date() : LocalDateTime.now();
		loan.setLoanDate(java.sql.Timestamp.valueOf(loanDate));
		loan.setReturnDate(java.sql.Timestamp.valueOf(data.returnDate()));
		return loanRepository.save(loan);
	}
}