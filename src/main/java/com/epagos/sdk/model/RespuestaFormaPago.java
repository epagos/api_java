package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaFormaPago {
    @JsonProperty("codigo_pago_fp")
    public Object codigoPagoFp;

    @JsonProperty("codigo_barras_fp")
    public String codigoBarrasFp;

    @JsonProperty("fechavenc_fp")
    public String fechavencFp;

    @JsonProperty("importe_fp")
    public BigDecimal importeFp;

    @JsonProperty("respuesta_entidad_cobro")
    public String respuestaEntidadCobro;

    @JsonProperty("barras_adicionales")
    public List<BarraAdicional> barrasAdicionales;
}
