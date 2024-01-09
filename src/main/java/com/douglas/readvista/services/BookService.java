package com.douglas.readvista.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.dtos.BookDTO;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository; 

	public Book findById(Integer id) {
		Optional<Book> obj = bookRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object with ID " + id + " not found."));
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book create(@Valid BookDTO objDTO) {
		objDTO.setId(null);
		Book newObj = new Book(objDTO);
		return bookRepository.save(newObj);
	}	
}
