package com.adm.restaurante.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("locator")
	private String locator;
	
	@JsonProperty("persons")
	private Long persons;
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("turnId")
	private String turnoId;
	
	@JsonProperty("restaurantId")
	private Long restaurantId;

}
