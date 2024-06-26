package com.douglas.readvista.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.douglas.readvista.dtos.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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
	
	private Boolean active;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Loan> loans = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "library_id")
	private Library library;

	public Customer(Integer id, String name, String cpf, String email, Library library, String password) {
		super();
		this.active = true;
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.library = library;
		this.password = password;
	}
	
	public Customer(CustomerDTO objDTO) {
		this.active = true;
		this.id = objDTO.id();
		this.name = objDTO.name();
		this.cpf = objDTO.cpf();
		this.email = objDTO.email();
		this.password = objDTO.password();
	}

}
