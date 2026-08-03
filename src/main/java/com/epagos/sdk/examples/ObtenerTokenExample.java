package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.EpagosResult;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.TokenResponse;

public final class ObtenerTokenExample {
    private ObtenerTokenExample() {
    }

    public static void main(String[] args) {
        EpagosClient client = ExampleConfig.client();
        Credenciales credenciales = ExampleConfig.credenciales();

        EpagosResult<Credenciales, TokenResponse> result = client.obtenerToken(credenciales);
        System.out.println(result.getRawBody());
    }
}



