package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SolicitudPagoRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("convenio")
    public Integer convenio;

    @JsonProperty("operacion")
    public Operacion operacion;

    @JsonProperty("fp")
    public List<FormaPago> fp;

    public SolicitudPagoRequest() {
    }

    public SolicitudPagoRequest(Integer idOrganismo, String token, Integer convenio, Operacion operacion, List<FormaPago> fp) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.convenio = convenio;
        this.operacion = operacion;
        this.fp = fp;
    }
}
