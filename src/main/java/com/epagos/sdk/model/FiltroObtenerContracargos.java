package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltroObtenerContracargos {
    @JsonProperty("Numero")
    public Object numero;

    @JsonProperty("Estado")
    public String estado;

    @JsonProperty("Fecha_desde")
    public String fechaDesde;

    @JsonProperty("Fecha_hasta")
    public String fechaHasta;
}
