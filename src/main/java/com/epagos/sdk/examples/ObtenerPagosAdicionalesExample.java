package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.FiltroPagosAdicionales;
import com.epagos.sdk.model.ObtenerPagosAdicionalesRequest;
import com.epagos.sdk.model.ObtenerPagosAdicionalesResponse;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObtenerPagosAdicionalesExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private ObtenerPagosAdicionalesExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        FiltroPagosAdicionales pagos = new FiltroPagosAdicionales();
        pagos.fechaDesde = "2026-01-01";
        pagos.fechaHasta = "2026-01-31";

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        ObtenerPagosAdicionalesRequest request = new ObtenerPagosAdicionalesRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                pagos
        );

        System.out.println("Request obtenerPagosAdicionales:");
        System.out.println(toJson(request));

        try {
            EpagosResult<ObtenerPagosAdicionalesRequest, ObtenerPagosAdicionalesResponse> result =
                    client.obtenerPagosAdicionales(request);
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
