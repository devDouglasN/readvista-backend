package com.douglas.readvista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.readvista.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByCpf(String cpf);
	Optional<Customer> findByEmail(String email);
}
