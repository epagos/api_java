package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SolicitudLoteItem {
    @JsonProperty("convenio")
    public Integer convenio;

    @JsonProperty("operacion")
    public Operacion operacion;

    @JsonProperty("fp")
    public List<FormaPago> fp;

    public SolicitudLoteItem() {
    }

    public SolicitudLoteItem(Integer convenio, Operacion operacion, List<FormaPago> fp) {
        this.convenio = convenio;
        this.operacion = operacion;
        this.fp = fp;
    }
}
