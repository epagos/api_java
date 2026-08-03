package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntidadPago {
    @JsonProperty("id_fp")
    public Integer idFp;

    @JsonProperty("nombre_fp")
    public String nombreFp;

    @JsonProperty("tipo_fp")
    public String tipoFp;

    @JsonProperty("estado_fp")
    public String estadoFp;

    @JsonProperty("logos_fp")
    public LogosFormaPago logosFp;

    @JsonProperty("config_fp")
    public ConfigFormaPago configFp;

    @JsonProperty("adicional_fp")
    public String adicionalFp;

    @JsonProperty("monto_minimo_fp")
    public BigDecimal montoMinimoFp;

    @JsonProperty("monto_maximo_fp")
    public BigDecimal montoMaximoFp;

    @JsonProperty("tiempo_acreditacion_fp")
    public Integer tiempoAcreditacionFp;
}
