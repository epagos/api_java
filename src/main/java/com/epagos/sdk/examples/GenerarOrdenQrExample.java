package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.GenerarOrdenQrRequest;
import com.epagos.sdk.model.GenerarOrdenQrResponse;
import com.epagos.sdk.model.OrdenQr;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public final class GenerarOrdenQrExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private GenerarOrdenQrExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        OrdenQr orden = new OrdenQr(58, new BigDecimal("1000"));

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        GenerarOrdenQrRequest request = new GenerarOrdenQrRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                orden
        );

        System.out.println("Request generarOrdenQr:");
        System.out.println(toJson(request));

        try {
            EpagosResult<GenerarOrdenQrRequest, GenerarOrdenQrResponse> result = client.generarOrdenQr(request);
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
