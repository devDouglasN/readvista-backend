package com.douglas.readvista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douglas.readvista.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByCpf(String cpf);
	Optional<Customer> findByEmail(String email);
	
	@Query("""
			select c.active
			from Customer c
			where 
			c.id = :id
			""")
	Boolean existsByIdAndActiveTrue(Integer id);
}
