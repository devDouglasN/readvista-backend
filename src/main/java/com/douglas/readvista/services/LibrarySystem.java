package com.douglas.readvista.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.readvista.entities.Address;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.entities.Library;
import com.douglas.readvista.entities.Loan;
import com.douglas.readvista.entities.UserSS;
import com.douglas.readvista.enums.BookCondition;
import com.douglas.readvista.enums.Status;
import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.repositories.CustomerRepository;
import com.douglas.readvista.repositories.LibraryRepository;
import com.douglas.readvista.repositories.LoanRepository;
import com.douglas.readvista.repositories.UserRepository;

@Service
public class LibrarySystem implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		String email = "usuario@test.com";
		String password = "senha";
		String passwordEncrypted = passwordEncoder.encode(password);
		UserSS user = new UserSS(null, email, passwordEncrypted);
		if (!userRepository.existsByEmail(email)) {
			userRepository.save(user);
		}
		
		Address address = new Address("Jardim Paulista", "78145-184", "São Paulo", "Prédio 05", "1005");
		Library library = new Library(null, "Labirinto Literário", address);
		libraryRepository.save(library);
		
		Customer customer1 = new Customer(null, "Douglas Nascimento", "337.411.810-05", "douglas@mail.com", library, "senha");
		Customer customer2 = new Customer(null, "Luana Souza", "234.958.770-37", "luana.souza@mail.com", library, "senha");
		Customer customer3 = new Customer(null, "Lucas Silva", "564.065.300-04", "lucas.silva@mail.com", library, "senha");
		Customer customer4 = new Customer(null, "Maria Oliveira", "888.151.540-78", "maria.oliveira@mail.com", library, "senha");
		Customer customer5 = new Customer(null, "Beatriz Pereira", "691.905.580-57", "beatriz.pereira@mail.com", library, "senha");
		Customer customer6 = new Customer(null, "Gabriel Almeida", "308.172.190-30", "gabriel.almeida@mail.com", library, "senha");
		List<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4, customer5, customer6);
		List<Customer> saveCustomers = customerRepository.saveAll(customers);
	
		
		Book book1 = new Book(null, "A Guerra dos Tronos", "https://m.media-amazon.com/images/I/91+1SUO3vUL._AC_UF1000,1000_QL80_.jpg", 
				"George R. R. Martin", "1996", Status.BORROWED, BookCondition.GOOD);
		
		Book book2 = new Book(null, "A fúria dos reis: As Crônicas de Gelo e Fogo", "https://m.media-amazon.com/images/I/91PglZzF9kL._AC_UF1000,1000_QL80_.jpg", 
				"George R. R. Martin", "1998", Status.OVERDUE, BookCondition.GOOD);
		
		Book book3 = new Book(null, "O Nome do Vento", "https://m.media-amazon.com/images/I/81CGmkRG9GL._AC_UF1000,1000_QL80_.jpg", 
				"Patrick Rothfuss", "2010", Status.RETURNED, BookCondition.GOOD);
		
		Book book4 = new Book(null, "O Caminho dos Reis", "https://www.editoratrama.com.br/wp-content/uploads/2022/07/capaLivro-152440_original-1-min-scaled.jpg",
				"Brandon Sanderson", "1998", Status.RETURNED, BookCondition.GOOD);
		
		Book book5 = new Book(null, "As Mentiras de Locke Lamora", "https://m.media-amazon.com/images/I/91ZA3Dd3yOL._AC_UF1000,1000_QL80_.jpg",
				"Scott Lynch", "2006", Status.RETURNED, BookCondition.GOOD);
		
		Book book6 = new Book(null, "A Lâmina em Si", "https://m.media-amazon.com/images/I/71Ir4pdJxHL._AC_UF1000,1000_QL80_.jpg",
				"Joe Abercrombie", "2006", Status.RETURNED, BookCondition.GOOD);
		
		Book book7 = new Book(null, "A Quinta Estação", "https://m.media-amazon.com/images/I/81Qnzqi3XlL._AC_UF1000,1000_QL80_.jpg",
				" N.K. Jemisin", "2015", Status.OVERDUE, BookCondition.DAMAGED);
		
		List<Book> books = Arrays.asList(book1, book2, book3, book4, book5, book6, book7);
		List<Book> saveBooks = bookRepository.saveAll(books);
		
		
		Loan loan1 = new Loan(null, saveBooks.get(0), saveCustomers.get(0));
		Loan loan2 = new Loan(null, saveBooks.get(1), saveCustomers.get(1));
		List<Loan> loans = Arrays.asList(loan1, loan2);
		loanRepository.saveAll(loans);		
	}	
}
