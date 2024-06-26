package com.douglas.readvista.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Loan implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date loanDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date returnDate;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public Loan(Integer id, Book book, Customer customer, Date loanDate, Date returnDate) {
	    this.id = id;
	    this.book = book;
	    this.customer = customer;
	    this.loanDate = loanDate;
	    this.returnDate = returnDate;
	}

	
}
