package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltroObtenerPagos {
    @JsonProperty("CodigoUnicoTransaccion")
    public Integer codigoUnicoTransaccion;

    @JsonProperty("ExternoId")
    public String externoId;

    @JsonProperty("ExternoId_2")
    public String externoId2;

    @JsonProperty("ExternoId_3")
    public String externoId3;

    @JsonProperty("Estado")
    public String estado;

    @JsonProperty("CodBarras")
    public String codBarras;

    @JsonProperty("Implementador")
    public Integer implementador;

    @JsonProperty("FechaPagoDesde")
    public String fechaPagoDesde;

    @JsonProperty("FechaPagoHasta")
    public String fechaPagoHasta;

    @JsonProperty("FechaAcreditacionDesde")
    public String fechaAcreditacionDesde;

    @JsonProperty("FechaAcreditacionHasta")
    public String fechaAcreditacionHasta;

    @JsonProperty("FechaNovedadAcreditacionDesde")
    public String fechaNovedadAcreditacionDesde;

    @JsonProperty("FechaNovedadAcreditacionHasta")
    public String fechaNovedadAcreditacionHasta;

    @JsonProperty("DevolverID4")
    public Boolean devolverId4;

    @JsonProperty("Pagina")
    public Integer pagina;

    public FiltroObtenerPagos copy() {
        FiltroObtenerPagos copy = new FiltroObtenerPagos();
        copy.codigoUnicoTransaccion = codigoUnicoTransaccion;
        copy.externoId = externoId;
        copy.externoId2 = externoId2;
        copy.externoId3 = externoId3;
        copy.estado = estado;
        copy.codBarras = codBarras;
        copy.implementador = implementador;
        copy.fechaPagoDesde = fechaPagoDesde;
        copy.fechaPagoHasta = fechaPagoHasta;
        copy.fechaAcreditacionDesde = fechaAcreditacionDesde;
        copy.fechaAcreditacionHasta = fechaAcreditacionHasta;
        copy.fechaNovedadAcreditacionDesde = fechaNovedadAcreditacionDesde;
        copy.fechaNovedadAcreditacionHasta = fechaNovedadAcreditacionHasta;
        copy.devolverId4 = devolverId4;
        copy.pagina = pagina;
        return copy;
    }
}
