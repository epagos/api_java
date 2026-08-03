package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObtenerCajasQrRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    public ObtenerCajasQrRequest() {
    }

    public ObtenerCajasQrRequest(Integer idOrganismo, String token) {
        this.idOrganismo = idOrganismo;
        this.token = token;
    }
}
