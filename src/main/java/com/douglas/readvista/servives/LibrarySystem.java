package com.douglas.readvista.servives;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.entities.Address;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.entities.Library;
import com.douglas.readvista.entities.Loan;
import com.douglas.readvista.enums.Status;
import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.repositories.CustomerRepository;
import com.douglas.readvista.repositories.LibraryRepository;
import com.douglas.readvista.repositories.LoanRepository;

@Service
public class LibrarySystem {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	public void startSystem() {
		
		Address address = new Address("Jardim Paulista", "78145-184", "São Paulo", "Prédio 05", "1005");
		Library library = new Library(null, "Labirinto Literário", address);
		libraryRepository.save(library);
		
		Customer customer1 = new Customer(null, "Douglas Nascimento", "337.411.810-05", "douglas@mail.com", "888");
		Customer customer2 = new Customer(null, "Larissa Martins", "234.958.770-37", "lari@mail.com", "888");
		List<Customer> customers = Arrays.asList(customer1, customer2);
		List<Customer> saveCustomers = customerRepository.saveAll(customers);
	
		
		Book book1 = new Book(null, "A Guerra dos Tronos", "George R. R. Martin", "1996", Status.BORROWED);
		Book book2 = new Book(null, "A fúria dos reis: As Crônicas de Gelo e Fogo", "George R. R. Martin", "1998", Status.OVERDUE);
		List<Book> books = Arrays.asList(book1, book2);
		List<Book> saveBooks = bookRepository.saveAll(books);
		
		
		Loan loan1 = new Loan(null, saveBooks.get(0), saveCustomers.get(0));
		Loan loan2 = new Loan(null, saveBooks.get(1), saveCustomers.get(1));
		List<Loan> loans = Arrays.asList(loan1, loan2);
		loanRepository.saveAll(loans);
	}
	
}
