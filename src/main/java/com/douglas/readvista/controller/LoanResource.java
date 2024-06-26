package com.douglas.readvista.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.douglas.readvista.dtos.LoanDTO;
import com.douglas.readvista.services.LoanService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/loans")
@SecurityRequirement(name = "bearer-key")
public class LoanResource {

    @Autowired
    private LoanService service;

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<LoanDTO> create(@RequestBody LoanDTO loanDTO) {
        LoanDTO newLoan = service.create(loanDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newLoan.id()).toUri();
        return ResponseEntity.created(uri).body(newLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> update(@PathVariable Integer id, @RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(service.update(id, loanDTO));
    }
}
