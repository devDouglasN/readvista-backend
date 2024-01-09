package com.douglas.readvista.dtos;

import java.io.Serializable;

import com.douglas.readvista.entities.Book;
import com.douglas.readvista.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BookDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private String author;
	private String yearOfPublication;

	@Enumerated(EnumType.STRING)
	private Status status;

	public BookDTO() {
	}

	public BookDTO(Book book) {
		super();
		this.id = book.getId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.yearOfPublication = book.getYearOfPublication();
		this.status = book.getStatus();
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
