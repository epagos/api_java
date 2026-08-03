package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.FormaPago;
import com.epagos.sdk.model.Operacion;
import com.epagos.sdk.model.SolicitudPagoRequest;
import com.epagos.sdk.model.SolicitudPagoResponse;
import com.epagos.sdk.model.TokenResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

public final class SolicitudPagoExample {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private SolicitudPagoExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        BigDecimal monto = new BigDecimal("200.00");
        Operacion operacion = ExampleConfig.operacion("JAVA-" + System.currentTimeMillis(), monto);
        List<FormaPago> formasPago = List.of(new FormaPago(4, monto));

        Credenciales credenciales = ExampleConfig.credenciales();
        EpagosResult<Credenciales, TokenResponse> tokenResult = client.obtenerToken(credenciales);
        SolicitudPagoRequest request = new SolicitudPagoRequest(
                credenciales.idOrganismo,
                tokenResult.getResponse().token.trim(),
                ExampleConfig.convenio(),
                operacion,
                formasPago
        );

        System.out.println("Request solicitudPagos:");
        System.out.println(toJson(request));

        try {
            EpagosResult<SolicitudPagoRequest, SolicitudPagoResponse> result = client.solicitudPagos(request);
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
