package com.douglas.readvista.dtos;

import java.util.Date;

import com.douglas.readvista.entities.Loan;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanDTO {

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date loanDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date returnDate;

	public LoanDTO(Loan objDTO) {
		super();
		this.id = objDTO.getId();
		this.loanDate = objDTO.getLoanDate();
		this.returnDate = objDTO.getReturnDate();
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
}
