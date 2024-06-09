package com.douglas.readvista.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	public Book() {
	}
	
	public Book(Integer id, String imagePath, String title, String author, String yearOfPublication, Status status, BookCondition bookCondition) {
		super();
		this.active = true;
		this.id = id;
		this.title = title;
		this.author = author;
		this.yearOfPublication = yearOfPublication;
		this.status = status;
		this.bookCondition = bookCondition;
	}
	
	public Book(BookDTO objDTO) {
		this.active = true;
		this.id = objDTO.getId();
		this.title = objDTO.getTitle();
		this.author = objDTO.getAuthor();
		this.yearOfPublication = objDTO.getYearOfPublication();
		this.status = objDTO.getStatus();
		this.bookCondition = objDTO.getCondition();
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public BookCondition getCondition() {
		return bookCondition;
	}

	public void setCondition(BookCondition bookCondition) {
		this.bookCondition = bookCondition;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}
}
