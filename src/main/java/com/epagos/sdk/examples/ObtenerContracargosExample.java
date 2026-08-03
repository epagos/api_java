package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.FiltroObtenerContracargos;
import com.epagos.sdk.model.ObtenerContracargosRequest;
import com.epagos.sdk.model.ObtenerContracargosResponse;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObtenerContracargosExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private ObtenerContracargosExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        FiltroObtenerContracargos contracargos = new FiltroObtenerContracargos();
        contracargos.numero = "CC-6195010";

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        ObtenerContracargosRequest request = new ObtenerContracargosRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                contracargos
        );

        System.out.println("Request obtenerContracargos:");
        System.out.println(toJson(request));

        try {
            EpagosResult<ObtenerContracargosRequest, ObtenerContracargosResponse> result =
                    client.obtenerContracargos(request);
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
