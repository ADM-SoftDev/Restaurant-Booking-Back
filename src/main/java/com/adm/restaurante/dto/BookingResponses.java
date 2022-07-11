package com.adm.restaurante.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponses<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private String code;
	private String message;
	private T data;
	
	
	public BookingResponses(String status, String code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
