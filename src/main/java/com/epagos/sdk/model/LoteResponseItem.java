package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoteResponseItem {
    @JsonProperty("id_transaccion")
    public Long idTransaccion;

    @JsonProperty("numero_operacion")
    public Object numeroOperacion;

    @JsonProperty("convenio")
    public Integer convenio;

    @JsonProperty("respuesta_forma_pago_array")
    public List<RespuestaFormaPago> respuestaFormaPagoArray;
}
