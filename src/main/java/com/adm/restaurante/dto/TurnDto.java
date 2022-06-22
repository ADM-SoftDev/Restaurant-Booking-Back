package com.adm.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/*
 * JSON datos que devolvera al Front
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TurnDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("restaurantId")
	private Long restaurantId;

}
