package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerarOrdenQrRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("orden")
    public OrdenQr orden;

    public GenerarOrdenQrRequest() {
    }

    public GenerarOrdenQrRequest(Integer idOrganismo, String token, OrdenQr orden) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.orden = orden;
    }
}
