package com.adm.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardJsonRest {

    @JsonProperty("numeroPersonas")
    private Long numeroPersonas;

    @JsonProperty("numeroMesa")
    private Long numeroMesa;

    @JsonProperty("restaurantId")
    private Long restaurantId;
}
