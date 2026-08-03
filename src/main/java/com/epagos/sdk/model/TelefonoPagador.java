package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TelefonoPagador {
    @JsonProperty("codigo_telef_pagador")
    public Integer codigoTelefPagador;

    @JsonProperty("numero_telef_pagador")
    public Long numeroTelefPagador;
}
