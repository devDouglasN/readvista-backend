package com.douglas.readvista.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {

	private Integer id;
	private String nameLibrary;
	private Address address;

	private List<Book> list = new ArrayList<>();

	public Library() {
	}

	public Library(Integer id, String nameLibrary, List<Book> list, Address address) {
		super();
		this.id = id;
		this.nameLibrary = nameLibrary;
		this.list = list;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameLibrary() {
		return nameLibrary;
	}

	public void setNameLibrary(String nameLibrary) {
		this.nameLibrary = nameLibrary;
	}

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		Library other = (Library) obj;
		return Objects.equals(id, other.id);
	}
}
