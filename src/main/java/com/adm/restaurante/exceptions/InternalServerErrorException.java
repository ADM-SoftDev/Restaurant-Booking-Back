package com.adm.restaurante.exceptions;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import com.adm.restaurante.dto.ErrorDto;

public class InternalServerErrorException extends BookingExceptions{

	private static final long serialVersionUID = 1L;
	
	public InternalServerErrorException(String code, String mensaje) {
		super(mensaje, HttpStatus.INTERNAL_SERVER_ERROR.value(),code);
	
	}
	
	public InternalServerErrorException(String code, String mensaje, ErrorDto data) {
		super(mensaje, HttpStatus.INTERNAL_SERVER_ERROR.value(),code, Arrays.asList(data));
	
	}
	

}
