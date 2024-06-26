package com.douglas.readvista.services;

import java.util.List;
import java.util.stream.Collectors;

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

    public LoanDTO findById(Integer id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Loan not found with ID: " + id));
        return mapToDTO(loan);
    }

    public List<LoanDTO> findAll() {
        return loanRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public LoanDTO update(Integer id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Loan not found with ID: " + id));
        loan.setLoanDate(loanDTO.loanDate());
        loan.setReturnDate(loanDTO.returnDate());
        loan.setBook(bookService.findBookById(loanDTO.book()));
        loan.setCustomer(customerService.findCustomerById(loanDTO.customer()));
        loan = loanRepository.save(loan);
        return mapToDTO(loan);
    }

    public LoanDTO create(LoanDTO loanDTO) {
        Book book = bookService.findBookById(loanDTO.book()); 
        Customer customer = customerService.findCustomerById(loanDTO.customer());
        Loan loan = new Loan(null, book, customer, loanDTO.loanDate(), loanDTO.returnDate());
        loan = loanRepository.save(loan);
        return mapToDTO(loan);
    }

    private LoanDTO mapToDTO(Loan loan) {
        return new LoanDTO(
            loan.getId(), 
            loan.getLoanDate(), 
            loan.getReturnDate(), 
            loan.getBook().getId(), 
            loan.getCustomer().getId(), 
            loan.getBook().getTitle(), 
            loan.getCustomer().getName()
        );
    }
}
