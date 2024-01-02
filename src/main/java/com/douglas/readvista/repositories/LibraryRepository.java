package com.douglas.readvista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.readvista.entities.Library;

public interface LibraryRepository extends JpaRepository<Library, Integer>{
}
