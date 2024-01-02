package com.douglas.readvista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.readvista.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
