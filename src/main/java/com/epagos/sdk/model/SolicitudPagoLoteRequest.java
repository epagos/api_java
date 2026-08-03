package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SolicitudPagoLoteRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("lote")
    public List<SolicitudLoteItem> lote;

    public SolicitudPagoLoteRequest() {
    }

    public SolicitudPagoLoteRequest(Integer idOrganismo, String token, List<SolicitudLoteItem> lote) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.lote = lote;
    }
}
