package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class OrdenQr {
    @JsonProperty("id_caja")
    public Integer idCaja;

    @JsonProperty("id_transaccion")
    public Long idTransaccion;

    @JsonProperty("importe")
    public BigDecimal importe;

    @JsonProperty("concepto")
    public String concepto;

    @JsonProperty("vencimiento")
    public String vencimiento;

    @JsonProperty("identificador_2")
    public String identificador2;

    @JsonProperty("identificador_3")
    public String identificador3;

    @JsonProperty("identificador_4")
    public String identificador4;

    @JsonProperty("email_pagador")
    public String emailPagador;

    @JsonProperty("detalle_orden")
    public String detalleOrden;

    public OrdenQr() {
    }

    public OrdenQr(Integer idCaja, BigDecimal importe) {
        this.idCaja = idCaja;
        this.importe = importe;
    }

    public OrdenQr(Long idTransaccion, BigDecimal importe) {
        this.idTransaccion = idTransaccion;
        this.importe = importe;
    }
}
