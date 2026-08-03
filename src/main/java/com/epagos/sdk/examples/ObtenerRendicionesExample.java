package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.FiltroObtenerRendiciones;
import com.epagos.sdk.model.ObtenerRendicionesRequest;
import com.epagos.sdk.model.ObtenerRendicionesResponse;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObtenerRendicionesExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private ObtenerRendicionesExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        FiltroObtenerRendiciones rendicion = new FiltroObtenerRendiciones();
        rendicion.fechaDesde = "2026-01-01";
        rendicion.fechaHasta = "2026-01-31";

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        ObtenerRendicionesRequest request = new ObtenerRendicionesRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                rendicion
        );

        System.out.println("Request obtenerRendiciones:");
        System.out.println(toJson(request));

        try {
            EpagosResult<ObtenerRendicionesRequest, ObtenerRendicionesResponse> result =
                    client.obtenerRendiciones(request);
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
