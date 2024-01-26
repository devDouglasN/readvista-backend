package com.douglas.readvista.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douglas.readvista.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

	boolean existsByBookIdAndCustomerId(Integer idBook, Integer idCustomer);

	@Query("""
			SELECT l.loanDate
			FROM Loan l
			WHERE l.book.id = :idBook
			AND l.customer.id = :idCustomer
			""")
	LocalDateTime findLoanDateByBookIdAndCustomerId(Integer idBook, Integer idCustomer);
}
