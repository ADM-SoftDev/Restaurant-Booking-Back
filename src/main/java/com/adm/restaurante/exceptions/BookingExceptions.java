package com.adm.restaurante.exceptions;

import java.util.ArrayList;
import java.util.List;
import com.adm.restaurante.dto.ErrorDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingExceptions extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String Mensaje;
	private final int responseCode;
	private final List<ErrorDto> errorList = new ArrayList<>();
	
	
	public BookingExceptions (String code,int responseCode , String mensaje) {
		super(code);
		this.Mensaje = mensaje;
		this.responseCode = responseCode;
	}
	
	public BookingExceptions (String code,int responseCode , String mensaje, List<ErrorDto> errorList) {
		super(code);
		this.Mensaje = mensaje;
		this.responseCode = responseCode;
		this.errorList.addAll(errorList);
		
	}
	
	

}
