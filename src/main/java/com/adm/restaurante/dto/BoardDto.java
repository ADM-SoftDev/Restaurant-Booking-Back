package com.adm.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto {
	
	@JsonProperty("ID_BOARD")
	private Long id_BOARD;
	
	@JsonProperty("capacity")
	private Long capacity;
	
	@JsonProperty("number")
	private Long number;

	@JsonProperty("restaurant_Id")
	private Long restaurant_Id;

}
