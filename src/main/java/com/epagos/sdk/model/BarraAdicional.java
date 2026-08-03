package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BarraAdicional {
    @JsonProperty("codigo_barra")
    public String codigoBarra;

    @JsonProperty("codigo_pago")
    public String codigoPago;

    @JsonProperty("fechavenc")
    public String fechavenc;

    @JsonProperty("importe")
    public BigDecimal importe;
}
