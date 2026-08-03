package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperacionLote {
    @JsonProperty("id_operacion")
    public Integer idOperacion;

    public OperacionLote() {
    }

    public OperacionLote(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }
}
