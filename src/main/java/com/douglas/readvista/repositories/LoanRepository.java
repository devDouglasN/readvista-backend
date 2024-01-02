package com.douglas.readvista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.readvista.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{
}
