package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FormaPago {
    @JsonProperty("id_fp")
    public Integer idFp;

    @JsonProperty("monto_fp")
    public BigDecimal montoFp;

    @JsonProperty("tarjeta")
    public Tarjeta tarjeta;

    public FormaPago() {
    }

    public FormaPago(Integer idFp, BigDecimal montoFp) {
        this.idFp = idFp;
        this.montoFp = montoFp;
    }
}
