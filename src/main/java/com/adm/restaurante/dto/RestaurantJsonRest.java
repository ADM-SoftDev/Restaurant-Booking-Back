package com.adm.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantJsonRest {

    @JsonProperty("nombre")
    private String nonmbre;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("imagen")
    private String imagen;
}
