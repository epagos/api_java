package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.OperacionPagoLote;
import com.epagos.sdk.model.PagoLote;
import com.epagos.sdk.model.PagoLoteRequest;
import com.epagos.sdk.model.PagoLoteResponse;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;


public final class PagoLoteExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private PagoLoteExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        PagoLote pagoLote = new PagoLote(
                6310349L,
                1,
                "2026-06-20",
                new BigDecimal("300.0"),
                List.of(
                        new OperacionPagoLote(6309981L, new BigDecimal("100.0")),
                        new OperacionPagoLote(6310003L, new BigDecimal("200.0"))
                )
        );

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        PagoLoteRequest request = new PagoLoteRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                pagoLote
        );

        System.out.println("Request pagoLote:");
        System.out.println(toJson(request));

        try {
            EpagosResult<PagoLoteRequest, PagoLoteResponse> result = client.pagoLote(request);
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
