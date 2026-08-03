package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObtenerPagosRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("pago")
    public FiltroObtenerPagos pago;

    public ObtenerPagosRequest() {
    }

    public ObtenerPagosRequest(Integer idOrganismo, String token, FiltroObtenerPagos pago) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.pago = pago;
    }
}
