package com.adm.restaurante.exceptions;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import com.adm.restaurante.dto.ErrorDto;

public class NotFoundException extends BookingExceptions{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String code, String mensaje) {
		super(mensaje, HttpStatus.NOT_FOUND.value(),code);
	
	}
	
	
	public NotFoundException(String code, String mensaje, ErrorDto data) {
		super(mensaje, HttpStatus.NOT_FOUND.value(),code, Arrays.asList(data));
	
	}
	

}
