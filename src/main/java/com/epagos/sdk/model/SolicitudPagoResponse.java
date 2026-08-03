package com.epagos.sdk.model;

import com.epagos.sdk.EpagosResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitudPagoResponse implements EpagosResponse {
    @JsonProperty("id_resp")
    public Integer idResp;

    @JsonProperty("respuesta")
    public String respuesta;

    @JsonProperty("id_transaccion")
    public Long idTransaccion;

    @JsonProperty("convenio")
    public Integer convenio;

    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("token")
    public String token;

    @JsonProperty("id_fp")
    public Integer idFp;

    @JsonProperty("identificador_2")
    public String identificador2;

    @JsonProperty("identificador_3")
    public String identificador3;

    @JsonProperty("identificador_4")
    public String identificador4;

    @JsonProperty("numero_operacion")
    public Object numeroOperacion;

    @JsonProperty("codigo_pago_fp")
    public Object codigoPagoFp;

    @JsonProperty("codigo_barras_fp")
    public String codigoBarrasFp;

    @JsonProperty("codigo_qr_T30_texto")
    public String codigoQrT30Texto;

    @JsonProperty("url_qr")
    public String urlQr;

    @JsonProperty("pdf")
    public String pdf;

    @JsonProperty("codigo_barras_imagen")
    public String codigoBarrasImagen;

    @JsonProperty("codigo_qr")
    public String codigoQr;

    @JsonProperty("codigo_pmc")
    public String codigoPmc;

    @JsonProperty("codigo_link")
    public String codigoLink;

    @JsonProperty("barras_adicionales")
    public List<Object> barrasAdicionales;

    @JsonProperty("fp")
    public List<Map<String, Object>> fp;

    @Override
    public Integer getIdResp() {
        return idResp;
    }
}
