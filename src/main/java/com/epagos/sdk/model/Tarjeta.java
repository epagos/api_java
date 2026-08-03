package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tarjeta {
    @JsonProperty("numero_tarjeta_fp")
    public String numeroTarjetaFp;

    @JsonProperty("banco_tarjeta_fp")
    public String bancoTarjetaFp;

    @JsonProperty("vencimiento_tarjeta_fp")
    public ExpiracionTarjeta vencimientoTarjetaFp;

    @JsonProperty("codigo_seg_tarjeta_fp")
    public String codigoSegTarjetaFp;

    @JsonProperty("cuotas_tarjeta_fp")
    public Integer cuotasTarjetaFp;

    @JsonProperty("titular_tarjeta_fp")
    public String titularTarjetaFp;

    @JsonProperty("identificacion_tarjeta_fp")
    public InfoTarjeta identificacionTarjetaFp;

    @JsonProperty("fechanac_tarjeta_fp")
    public String fechanacTarjetaFp;

    @JsonProperty("direccion_tarjeta_fp")
    public DireccionTarjeta direccionTarjetaFp;
}
