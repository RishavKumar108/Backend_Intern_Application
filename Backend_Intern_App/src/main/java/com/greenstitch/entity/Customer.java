package com.greenstitch.entity;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long customerId;

	@NotNull(message = "Name cannot be null!")
	@NotBlank(message = "Name connot be blank!")
	@Size(min = 3, max = 20 , message = "firstName must be 8-15 characters long")
	private String firstName;
	
	@NotNull(message = "Name cannot be null!")
	@NotBlank(message = "Name connot be blank!")
	@Size(min = 3, max = 20, message = "lastName must be 8-15 characters")
	private String lastName;
	
	@NotNull(message="Password cannot be null!")
	@NotBlank(message= "Password cannot be blank!")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in and include alphanumerics and special characters")
	private String password;
	
	
	@NotNull(message="Mobile number cannot be null!")
	@NotBlank(message= "Mobile number cannot be blank!")
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String phone;
	
	@Email
	@Column(unique =true)
	private String email;
	
	@Enumerated
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Role role;

	public void setRole (Role roleCustomer) {
		// TODO Auto-generated method stub
		this.role = roleCustomer;
	}
	
}