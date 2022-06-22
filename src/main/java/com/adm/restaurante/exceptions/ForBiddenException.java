package com.adm.restaurante.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.adm.restaurante.dto.ErrorDto;

public class ForBiddenException extends BookingExceptions{

	private static final long serialVersionUID = 1L;
	
	public ForBiddenException(String code, String mensaje) {
		super(mensaje, HttpStatus.FORBIDDEN.value(),code);
	
	}

	public ForBiddenException(String code, String mensaje, ErrorDto data) {
		super(mensaje, HttpStatus.FORBIDDEN.value(),code, Arrays.asList(data));
	
	}
	

}
