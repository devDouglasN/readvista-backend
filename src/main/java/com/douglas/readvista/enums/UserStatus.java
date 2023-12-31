package com.douglas.readvista.enums;

public enum UserStatus {
	ACTIVE(0, "Active"), INACTIVE(1, "Inactive");

	private Integer code;
	private String description;

	UserStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
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

	public static UserStatus statusFromCode(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (UserStatus x : UserStatus.values()) {
			if (cod.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid user status code!");
	}
}
