package com.adm.restaurante.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateReservationJsonRest {

	@JsonProperty("fecha")
	private Date fecha;
	
	@JsonProperty("personas")
	private Long personas;
	
	@JsonProperty("turnId")
	private Long turnId;
	
	@JsonProperty("restaurantId")
	private Long restaurantId;

	@JsonProperty("name")
	private String nombre;

	@JsonProperty("email")
	private String correo;
	
	
}
