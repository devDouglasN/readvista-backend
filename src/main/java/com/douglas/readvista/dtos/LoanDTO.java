package com.douglas.readvista.dtos;

import java.io.Serializable;
import java.util.Date;

import com.douglas.readvista.entities.Loan;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class LoanDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date loanDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date returnDate;

	@NotNull(message = "Field TECHNICIAN is required")
	private Integer book;
	@NotNull(message = "Field CUSTOMER is required")
	private Integer customer;
	private String bookName;
	private String customerName;
	
	public LoanDTO() {
	}

	public LoanDTO(Loan loan) {
		super();
		this.id = loan.getId();
		this.loanDate = loan.getLoanDate();
		this.returnDate = loan.getReturnDate();
		this.book = loan.getBook().getId();
		this.customer = loan.getCustomer().getId();
		this.bookName = loan.getBook().getTitle();
		this.customerName = loan.getCustomer().getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


}
