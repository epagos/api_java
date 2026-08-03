package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltroPagosAdicionales {
    @JsonProperty("Fecha_desde")
    public String fechaDesde;

    @JsonProperty("Fecha_hasta")
    public String fechaHasta;
}
