package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigFormaPago {
    @JsonProperty("patron_config_fp")
    public String patronConfigFp;

    @JsonProperty("longitud_config_fp")
    public Integer longitudConfigFp;

    @JsonProperty("validacion_config_fp")
    public String validacionConfigFp;

    @JsonProperty("codseg_config_fp")
    public String codsegConfigFp;

    @JsonProperty("codseg_long_config_fp")
    public Integer codsegLongConfigFp;

    @JsonProperty("codseg_ubic_config_fp")
    public String codsegUbicConfigFp;
}
