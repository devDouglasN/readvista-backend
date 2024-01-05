package com.douglas.readvista.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.dtos.CustomerDTO;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer findById(Integer id) {
		Optional<Customer> obj = customerRepository.findById(id);
		return obj.orElse(null);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer create(CustomerDTO objDTO) {
		objDTO.setId(null);
		Customer newObj = new Customer(objDTO);
		return customerRepository.save(newObj);
	}
}
