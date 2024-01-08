package com.douglas.readvista.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.dtos.CustomerDTO;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.repositories.CustomerRepository;
import com.douglas.readvista.services.exceptions.DataIntegrityViolationException;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer findById(Integer id) {
		Optional<Customer> obj = customerRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id object: " + id + " not found"));
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer create(CustomerDTO objDTO) {
		objDTO.setId(null);
		validationCpfAndEmail(objDTO);
		Customer newObj = new Customer(objDTO);
		return customerRepository.save(newObj);
	}
	
	public Customer update(Integer id, @Valid CustomerDTO objDTO) {
		objDTO.setId(id);
		Customer oldObj = findById(id);
		validationCpfAndEmail(objDTO);
		oldObj = new Customer(objDTO);
		return customerRepository.save(oldObj);
		
	}

	public void validationCpfAndEmail(CustomerDTO objDTO) {
		Optional<Customer> obj = customerRepository.findByCpf(objDTO.getCpf());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF already registered in the system!");
		}

		obj = customerRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email already registered in the system!");
		}
	}

	
}
