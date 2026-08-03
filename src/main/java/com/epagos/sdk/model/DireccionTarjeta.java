package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DireccionTarjeta {
    @JsonProperty("calle_direccion_tarjeta_fp")
    public String calleDireccionTarjetaFp;

    @JsonProperty("numero_direccion_tarjeta_fp")
    public String numeroDireccionTarjetaFp;
}
