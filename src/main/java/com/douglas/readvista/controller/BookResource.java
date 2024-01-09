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

import com.douglas.readvista.dtos.BookDTO;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/books")
public class BookResource {

	@Autowired
	private BookService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> findById(@PathVariable Integer id){
		Book obj = service.findById(id);
		return ResponseEntity.ok().body(new BookDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(){
		List<Book> list = service.findAll();
		List<BookDTO> listDTO = list.stream().map(BookDTO::new).collect(Collectors.toList());	
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO objDTO){
		Book newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(new BookDTO(newObj));
	}
}