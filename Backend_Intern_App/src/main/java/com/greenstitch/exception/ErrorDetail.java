package com.greenstitch.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {
	
	private LocalDateTime timestamp;
	private String message;
	private String details;

}
