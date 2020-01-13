package com.jprado.users.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {

	private String message;
	private Object[] args;
	
}
