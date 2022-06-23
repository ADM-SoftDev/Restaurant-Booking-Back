package com.adm.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TurnJsonRest {

    @JsonProperty("turno")
    private String turno;

    @JsonProperty("restaurantId")
    private Long restaurantId;
}
