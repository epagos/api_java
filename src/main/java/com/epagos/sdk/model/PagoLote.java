package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class PagoLote {
    @JsonProperty("id_transaccion")
    public Long idTransaccion;

    @JsonProperty("forma_pago")
    public Integer formaPago;

    @JsonProperty("fecha_pago")
    public String fechaPago;

    @JsonProperty("importe")
    public BigDecimal importe;

    @JsonProperty("operaciones")
    public List<OperacionPagoLote> operaciones;

    public PagoLote() {
    }

    public PagoLote(
            Long idTransaccion,
            Integer formaPago,
            String fechaPago,
            BigDecimal importe,
            List<OperacionPagoLote> operaciones
    ) {
        this.idTransaccion = idTransaccion;
        this.formaPago = formaPago;
        this.fechaPago = fechaPago;
        this.importe = importe;
        this.operaciones = operaciones;
    }
}
