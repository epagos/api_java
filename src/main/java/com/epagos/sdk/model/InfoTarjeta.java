package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoTarjeta {
    @JsonProperty("tipo_identificacion_tarjeta_fp")
    public Integer tipoIdentificacionTarjetaFp;

    @JsonProperty("numero_identificacion_tarjeta_fp")
    public Long numeroIdentificacionTarjetaFp;
}
