package com.douglas.readvista.entities;

import java.util.Objects;

import com.douglas.readvista.enums.Status;

public class Book {

	private Integer id;
	private String title;
	private String author;
	private String yearOfPublication;

	private Status status;

	public Book() {
	}
	
	public Book(Integer id, String title, String author, String yearOfPublication, Status status) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.yearOfPublication = yearOfPublication;
		this.status = status;
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
