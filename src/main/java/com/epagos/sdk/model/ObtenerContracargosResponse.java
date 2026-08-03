package com.epagos.sdk.model;

import com.epagos.sdk.EpagosResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObtenerContracargosResponse implements EpagosResponse {
    @JsonProperty("id_resp")
    public Integer idResp;

    @JsonProperty("respuesta")
    public String respuesta;

    @JsonProperty("token")
    public String token;

    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("contracargos")
    public List<Map<String, Object>> contracargos;

    @Override
    public Integer getIdResp() {
        return idResp;
    }
}
