package com.douglas.readvista.enums;

public enum Status {

	BORROWED(0, "burrowed"), RETURNED(1, "returned"), OVERDUE(2, "overdue");

	private Integer code;
	private String description;

	private Status(Integer code, String description) {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Status statusFromCode(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Status x : Status.values()) {
			if (cod.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Books unavailable!");
	}
}
