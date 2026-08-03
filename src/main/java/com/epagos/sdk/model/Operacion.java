package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class Operacion {
    @JsonProperty("numero_operacion")
    public String numeroOperacion;

    @JsonProperty("identificador_externo_2")
    public String identificadorExterno2;

    @JsonProperty("identificador_externo_3")
    public String identificadorExterno3;

    @JsonProperty("identificador_externo_4")
    public String identificadorExterno4;

    @JsonProperty("identificador_cliente")
    public String identificadorCliente;

    @JsonProperty("id_moneda_operacion")
    public Integer idMonedaOperacion;

    @JsonProperty("monto_operacion")
    public BigDecimal montoOperacion;

    @JsonProperty("opc_pdf")
    public Boolean opcPdf;

    @JsonProperty("opc_fecha_vencimiento")
    public String opcFechaVencimiento;

    @JsonProperty("opc_devolver_qr")
    public Boolean opcDevolverQr;

    @JsonProperty("opc_devolver_codbarras")
    public Boolean opcDevolverCodbarras;

    @JsonProperty("opc_generar_pdf")
    public Boolean opcGenerarPdf;

    @JsonProperty("opc_operaciones_lote")
    public List<OperacionLote> opcOperacionesLote;

    @JsonProperty("opc_fp_excluidas")
    public String opcFpExcluidas;

    @JsonProperty("opc_tp_excluidos")
    public String opcTpExcluidos;

    @JsonProperty("opc_fp_permitidas")
    public String opcFpPermitidas;

    @JsonProperty("detalle_operacion")
    public List<DetalleOperacion> detalleOperacion;

    @JsonProperty("pagador")
    public Pagador pagador;

    @JsonProperty("fecha_2do_venc")
    public String fecha2doVenc;

    @JsonProperty("monto_operacion_2do_venc")
    public BigDecimal montoOperacion2doVenc;

    @JsonProperty("tipo_operacion")
    public Integer tipoOperacion;

    @JsonProperty("codigo_publicacion")
    public Integer codigoPublicacion;

    @JsonProperty("url_boleta")
    public String urlBoleta;

    @JsonProperty("url_ok")
    public String urlOk;

    @JsonProperty("url_error")
    public String urlError;

    @JsonProperty("opc_T30_cerrado")
    public Boolean opcT30Cerrado;

    @JsonProperty("opc_T30_reutilizable")
    public Boolean opcT30Reutilizable;

    @JsonProperty("opc_T30_require_orden")
    public Boolean opcT30RequireOrden;

    @JsonProperty("opc_T30_requiere_orden")
    public Boolean opcT30RequiereOrden;
}
