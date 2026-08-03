package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ObtenerEntidadesPagoRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("fp")
    public List<Integer> fp;

    public ObtenerEntidadesPagoRequest() {
    }

    public ObtenerEntidadesPagoRequest(Integer idOrganismo, String token) {
        this(idOrganismo, token, null);
    }

    public ObtenerEntidadesPagoRequest(Integer idOrganismo, String token, List<Integer> fp) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.fp = fp;
    }
}
