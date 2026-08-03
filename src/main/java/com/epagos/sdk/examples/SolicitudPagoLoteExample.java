package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.FormaPago;
import com.epagos.sdk.model.SolicitudLoteItem;
import com.epagos.sdk.model.SolicitudPagoLoteRequest;
import com.epagos.sdk.model.SolicitudPagoLoteResponse;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

public final class SolicitudPagoLoteExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private SolicitudPagoLoteExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        int convenio = ExampleConfig.convenio();

        BigDecimal primerMonto = new BigDecimal("100.00");
        BigDecimal segundoMonto = new BigDecimal("150.00");

        List<SolicitudLoteItem> lote = List.of(
                new SolicitudLoteItem(
                        convenio,
                        ExampleConfig.operacion("JAVA-LOTE-1-" + System.currentTimeMillis(), primerMonto),
                        List.of(new FormaPago(4, primerMonto))
                ),
                new SolicitudLoteItem(
                        convenio,
                        ExampleConfig.operacion("JAVA-LOTE-2-" + System.currentTimeMillis(), segundoMonto),
                        List.of(new FormaPago(4, segundoMonto))
                )
        );

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        SolicitudPagoLoteRequest request = new SolicitudPagoLoteRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                lote
        );

        System.out.println("Request solicitudPagosLote:");
        System.out.println(toJson(request));

        try {
            EpagosResult<SolicitudPagoLoteRequest, SolicitudPagoLoteResponse> result = client.solicitudPagosLote(request);
            System.out.println(result.getRawBody());
        } catch (EpagosException e) {
            System.err.println(e.getMessage());
            if (e.getStatusCode() != null) {
                System.err.println("HTTP status: " + e.getStatusCode());
            }
            if (e.getRawBody() != null && !e.getRawBody().isBlank()) {
                System.err.println("Response:");
                System.err.println(e.getRawBody());
            }
            throw e;
        }
    }

    private static String toJson(Object value) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("No se pudo serializar la request.", e);
        }
    }
}
