package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.FiltroObtenerPagos;
import com.epagos.sdk.model.ObtenerPagosRequest;
import com.epagos.sdk.model.ObtenerPagosResponse;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObtenerPagosExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private ObtenerPagosExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        FiltroObtenerPagos payments = new FiltroObtenerPagos();
        payments.estado = "A";

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        ObtenerPagosRequest request = new ObtenerPagosRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                payments
        );

        System.out.println("Request obtenerPagos:");
        System.out.println(toJson(request));

        try {
            EpagosResult<ObtenerPagosRequest, ObtenerPagosResponse> result = client.obtenerPagos(request);
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
