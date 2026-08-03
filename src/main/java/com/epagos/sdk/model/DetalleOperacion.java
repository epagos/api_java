package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class DetalleOperacion {
    @JsonProperty("id_item")
    public Integer idItem;

    @JsonProperty("desc_item")
    public String descItem;

    @JsonProperty("monto_item")
    public BigDecimal montoItem;

    @JsonProperty("cantidad_item")
    public Integer cantidadItem;

    public DetalleOperacion() {
    }

    public DetalleOperacion(Integer idItem, String descItem, BigDecimal montoItem, Integer cantidadItem) {
        this.idItem = idItem;
        this.descItem = descItem;
        this.montoItem = montoItem;
        this.cantidadItem = cantidadItem;
    }
}
