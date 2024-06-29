package com.douglas.readvista.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.douglas.readvista.dtos.BookDTO;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.services.BookService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/books")
@SecurityRequirement(name = "bearer-key")
public class BookResource {

    @Autowired
    private BookService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Integer id){
        Book book = service.findBookById(id);
        BookDTO bookDTO = service.mapToDTO(book); 
        return ResponseEntity.ok(bookDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    
    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO){
        BookDTO createdBook = service.create(bookDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdBook.id()).toUri();
        return ResponseEntity.created(uri).body(createdBook);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Integer id, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(service.update(id, bookDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
