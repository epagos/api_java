package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObtenerPagosAdicionalesRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("pagos")
    public FiltroPagosAdicionales pagos;

    public ObtenerPagosAdicionalesRequest() {
    }

    public ObtenerPagosAdicionalesRequest(Integer idOrganismo, String token, FiltroPagosAdicionales pagos) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.pagos = pagos;
    }
}
