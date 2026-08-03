package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogosFormaPago {
    @JsonProperty("seguro_logos_fp")
    public String seguroLogosFp;

    @JsonProperty("nombre_logos_fp")
    public String nombreLogosFp;
}
