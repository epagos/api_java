package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpiracionTarjeta {
    @JsonProperty("mes_vencimiento_tarjeta_fp")
    public Integer mesVencimientoTarjetaFp;

    @JsonProperty("anio_vencimiento_tarjeta_fp")
    public Integer anioVencimientoTarjetaFp;
}
