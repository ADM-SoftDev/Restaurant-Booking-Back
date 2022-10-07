package com.adm.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentConfirmJsonRest {
    @JsonProperty("nombre")
    private String name;

    @JsonProperty("correo")
    private String email;

    @JsonProperty("idPago")
    private String paymentId;

    @JsonProperty("localizador")
    private String locator;

    //@JsonProperty("precio")
    //private int precio;

}
