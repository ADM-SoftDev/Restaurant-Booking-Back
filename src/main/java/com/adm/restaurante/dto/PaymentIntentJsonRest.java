package com.adm.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentIntentJsonRest {


    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("precio")
    private int price;


}
