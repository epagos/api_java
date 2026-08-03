package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltroObtenerRendiciones {
    @JsonProperty("Numero")
    public Integer numero;

    @JsonProperty("Secuencia")
    public Integer secuencia;

    @JsonProperty("Fecha_desde")
    public String fechaDesde;

    @JsonProperty("Fecha_hasta")
    public String fechaHasta;

    @JsonProperty("Fecha_deposito_desde")
    public String fechaDepositoDesde;

    @JsonProperty("Fecha_deposito_hasta")
    public String fechaDepositoHasta;
}
