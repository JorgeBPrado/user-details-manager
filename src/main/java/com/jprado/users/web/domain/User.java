package com.jprado.users.web.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

	@NotNull(message = ValidationConstants.COMMON_FIELD_NOT_NULL)
	@NotEmpty(message = ValidationConstants.COMMON_FIELD_NOT_EMPTY)
	private String fullname;
	
	@NotNull(message = ValidationConstants.COMMON_FIELD_NOT_NULL)
	@NotEmpty(message = ValidationConstants.COMMON_FIELD_NOT_EMPTY)
	private String email;
	
	private String description;
	

	
	
	
}
