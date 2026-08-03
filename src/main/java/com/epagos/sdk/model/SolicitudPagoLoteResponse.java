package com.epagos.sdk.model;

import com.epagos.sdk.EpagosResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitudPagoLoteResponse implements EpagosResponse {
    @JsonProperty("id_resp")
    public Integer idResp;

    @JsonProperty("respuesta")
    public String respuesta;

    @JsonProperty("token")
    public String token;

    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("lote")
    public List<LoteResponseItem> lote;

    @Override
    public Integer getIdResp() {
        return idResp;
    }
}
