package com.douglas.readvista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.readvista.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
}
