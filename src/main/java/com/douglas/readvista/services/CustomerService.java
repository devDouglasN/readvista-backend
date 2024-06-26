package com.douglas.readvista.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.dtos.CustomerDTO;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.entities.Library;
import com.douglas.readvista.repositories.CustomerRepository;
import com.douglas.readvista.repositories.LibraryRepository;
import com.douglas.readvista.services.exceptions.DataIntegrityViolationException;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private LibraryRepository libraryRepository;

    public Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Customer not found with ID: " + id));
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        validateCpfAndEmail(customerDTO);
        Library library = libraryRepository.findById(customerDTO.libraryId())
            .orElseThrow(() -> new ObjectNotFoundException("Library not found with ID: " + customerDTO.libraryId()));

        Customer customer = new Customer(null, customerDTO.name(), customerDTO.cpf(), customerDTO.email(), library, customerDTO.password());
        customer = customerRepository.save(customer);
        return mapToDTO(customer);
    }


    public CustomerDTO update(Integer id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Customer not found with ID: " + id));
        validateCpfAndEmail(customerDTO);
        customer.setName(customerDTO.name());
        customer.setCpf(customerDTO.cpf());
        customer.setEmail(customerDTO.email());
        customer.setPassword(customerDTO.password());
        customer = customerRepository.save(customer);
        return mapToDTO(customer);
    }

    public void delete(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Customer not found with ID: " + id));
        if (customer.getLoans().size() > 0) {
            throw new DataIntegrityViolationException("Customer has loans and cannot be deleted!");
        }
        customerRepository.deleteById(id);
    }

    private void validateCpfAndEmail(CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findByCpf(customerDTO.cpf());
        if (existingCustomer.isPresent() && !existingCustomer.get().getId().equals(customerDTO.id())) {
            throw new DataIntegrityViolationException("CPF already registered!");
        }
        existingCustomer = customerRepository.findByEmail(customerDTO.email());
        if (existingCustomer.isPresent() && !existingCustomer.get().getId().equals(customerDTO.id())) {
            throw new DataIntegrityViolationException("Email already registered!");
        }
    }

    private CustomerDTO mapToDTO(Customer customer) {
        return new CustomerDTO(
            customer.getId(), 
            customer.getName(), 
            customer.getCpf(), 
            customer.getEmail(), 
            customer.getPassword(),
            customer.getLibrary() != null ? customer.getLibrary().getId() : null  
        );
    }

}
