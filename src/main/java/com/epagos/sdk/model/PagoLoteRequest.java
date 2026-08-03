package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagoLoteRequest {
    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("pago_lote")
    public PagoLote pagoLote;

    public PagoLoteRequest() {
    }

    public PagoLoteRequest(Integer idOrganismo, String token, PagoLote pagoLote) {
        this.idOrganismo = idOrganismo;
        this.token = token;
        this.pagoLote = pagoLote;
    }
}
