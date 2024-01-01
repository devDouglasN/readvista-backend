package com.douglas.readvista.entities;

import java.util.Date;

public class Loan {

	private Integer id;
	
	private Book book;
	private Library library;
	
	private Date loanDate;
	private Date returnDate;
	
	public Loan() {
	}
	
	public Loan(Integer id, Book book, Library library, Date loanDate, Date returnDate) {
		super();
		this.id = id;
		this.book = book;
		this.library = library;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
