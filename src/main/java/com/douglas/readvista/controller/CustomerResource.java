package com.douglas.readvista.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.readvista.dtos.CustomerDTO;
import com.douglas.readvista.entities.Customer;
import com.douglas.readvista.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	@Autowired
	private CustomerService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
		Customer obj = service.findById(id);
		return ResponseEntity.ok().body(new CustomerDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> list = service.findAll();
		List<CustomerDTO> listDTO = list.stream().map(CustomerDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO objDTO) {
		Customer newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
