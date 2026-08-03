package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class OperacionPagoLote {
    @JsonProperty("id_transaccion")
    public Long idTransaccion;

    @JsonProperty("importe")
    public BigDecimal importe;

    public OperacionPagoLote() {
    }

    public OperacionPagoLote(Long idTransaccion, BigDecimal importe) {
        this.idTransaccion = idTransaccion;
        this.importe = importe;
    }
}
