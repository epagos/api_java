package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdentificacionPagador {
    @JsonProperty("tipo_doc_pagador")
    public Integer tipoDocPagador;

    @JsonProperty("numero_doc_pagador")
    public Long numeroDocPagador;

    @JsonProperty("cuit_doc_pagador")
    public Long cuitDocPagador;

    public IdentificacionPagador() {
    }

    public IdentificacionPagador(Integer tipoDocPagador, Long numeroDocPagador, Long cuitDocPagador) {
        this.tipoDocPagador = tipoDocPagador;
        this.numeroDocPagador = numeroDocPagador;
        this.cuitDocPagador = cuitDocPagador;
    }
}
