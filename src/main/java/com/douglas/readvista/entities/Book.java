package com.douglas.readvista.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.douglas.readvista.dtos.BookDTO;
import com.douglas.readvista.enums.BookCondition;
import com.douglas.readvista.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Book implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	 private String imagePath;
	
	private String title;
	private String author;
	private String yearOfPublication;
	
	private Boolean active;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private BookCondition bookCondition;
	
	@JsonIgnore
	@OneToMany(mappedBy = "book")
	private List<Loan> loans = new ArrayList<>();

	public Book(Integer id, String imagePath, String title, String author, String yearOfPublication, Status status, BookCondition bookCondition) {
        this.active = true;
        this.id = id;
        this.imagePath = imagePath;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.status = status;
        this.bookCondition = bookCondition;
    }
	
	public Book(BookDTO objDTO) {
		this.active = true;
		this.id = objDTO.id();
		this.title = objDTO.title();
		this.author = objDTO.author();
		this.yearOfPublication = objDTO.yearOfPublication();
		this.status = objDTO.status();
		this.bookCondition = objDTO.bookCondition();
	}

}
