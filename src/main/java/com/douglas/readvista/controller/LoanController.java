package com.douglas.readvista.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.readvista.dtos.LoanDTO;
import com.douglas.readvista.entities.Loan;
import com.douglas.readvista.loan.validators.BookLoanData;
import com.douglas.readvista.services.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/loans")
@SecurityRequirement(name = "bearer-key")
public class LoanController {

	@Autowired
	private LoanService service;

	@GetMapping("/{id}")
	public ResponseEntity<LoanDTO> findById(@PathVariable Integer id) {
		Loan obj = service.findById(id);
		return ResponseEntity.ok().body(new LoanDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<LoanDTO>> findAll() {
		List<Loan> list = service.findAll();
		List<LoanDTO> listDTO = list.stream().map(LoanDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<Loan> create(@Valid @RequestBody BookLoanData bookLoanData) {
		Loan newObj = service.loan(bookLoanData);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<LoanDTO> update(@PathVariable Integer id, @Valid @RequestBody LoanDTO objDTO) {
		Loan newLoan = service.update(id, objDTO);
		return ResponseEntity.ok().body(new LoanDTO(newLoan));
	}
}
