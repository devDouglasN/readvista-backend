package com.douglas.readvista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douglas.readvista.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("""
			select b.active
			from Book b
			where
			b.id = :id
			""")
	boolean existsByIdAndActiveTrue(Integer id);
}
