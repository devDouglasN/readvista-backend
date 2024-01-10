package com.douglas.readvista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.readvista.dtos.LoanDTO;
import com.douglas.readvista.entities.Loan;
import com.douglas.readvista.services.LoanService;

@RestController
@RequestMapping(value = "/loans")
public class LoanController {

	@Autowired
	private LoanService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<LoanDTO> findById(@PathVariable Integer id){
		Loan obj = service.findById(id);
		return ResponseEntity.ok().body(new LoanDTO(obj));
	}
}
