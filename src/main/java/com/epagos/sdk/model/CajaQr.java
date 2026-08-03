package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CajaQr {
    @JsonProperty("id_caja")
    public Integer idCaja;

    @JsonProperty("nombre_caja")
    public String nombreCaja;

    @JsonProperty("tipo_caja")
    public String tipoCaja;

    @JsonProperty("monto_maximo_caja")
    public BigDecimal montoMaximoCaja;
}
