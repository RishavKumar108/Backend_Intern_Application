package com.greenstitch.entity;

public enum Role {
	ROLE_ADMIN,ROLE_CUSTOMER;
	
	@Override
	public String toString() {
		return name().toString();
	}
}
