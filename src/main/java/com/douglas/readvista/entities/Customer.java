package com.douglas.readvista.entities;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.douglas.readvista.dtos.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@CPF
	@Column(unique = true)
	private String cpf;
	
	@Column(unique = true)
	private String email;
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Loan> loans;

	public Customer() {
	}

	public Customer(Integer id, String name, String cpf, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
	}
	
	public Customer(CustomerDTO objDTO) {
		super();
		this.id = objDTO.getId();
		this.name = objDTO.getName();
		this.cpf = objDTO.getCpf();
		this.email = objDTO.getEmail();
		this.password = objDTO.getPassword();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
}
