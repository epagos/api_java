package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObtenerContracargosRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("contracargos")
    public FiltroObtenerContracargos contracargos;

    public ObtenerContracargosRequest() {
    }

    public ObtenerContracargosRequest(Integer idOrganismo, String token, FiltroObtenerContracargos contracargos) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.contracargos = contracargos;
    }
}
