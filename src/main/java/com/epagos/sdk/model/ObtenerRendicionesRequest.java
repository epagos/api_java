package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObtenerRendicionesRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("rendicion")
    public FiltroObtenerRendiciones rendicion;

    public ObtenerRendicionesRequest() {
    }

    public ObtenerRendicionesRequest(Integer idOrganismo, String token, FiltroObtenerRendiciones rendicion) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.rendicion = rendicion;
    }
}
