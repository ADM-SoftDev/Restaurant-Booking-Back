package com.adm.restaurante.dto;

import java.util.List;
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
public class RestaurantDto {
	
	@JsonProperty("id_restaurante")
	private Long id_restaurante;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String descripcion;
	
	@JsonProperty("address")
	private String direccion;
	
	@JsonProperty("image")
	private String imagen;

	@JsonProperty("price")
	private Integer precio;
	
	@JsonProperty("turns")
	private List<TurnDto> turns;

}
