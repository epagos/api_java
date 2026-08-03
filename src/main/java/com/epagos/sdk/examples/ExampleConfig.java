package com.epagos.sdk.examples;

import com.epagos.sdk.EpagosClient;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.DetalleOperacion;
import com.epagos.sdk.model.IdentificacionPagador;
import com.epagos.sdk.model.Operacion;
import com.epagos.sdk.model.Pagador;

import java.math.BigDecimal;
import java.util.List;

final class ExampleConfig {
    private ExampleConfig() {
    }

    static EpagosClient client() {
        String environment = env("EPAGOS_ENVIRONMENT");
        if (environment != null && environment.equalsIgnoreCase("production")) {
            return EpagosClient.production();
        }

        return EpagosClient.sandbox();
    }

    static Credenciales credenciales() {
        return new Credenciales(
                requireEnv("EPAGOS_HASH"),
                requireEnv("EPAGOS_PASSWORD"),
                integerEnv("EPAGOS_ID_ORGANISMO"),
                integerEnv("EPAGOS_ID_USUARIO")
        );
    }

    static int convenio() {
        return integerEnv("EPAGOS_CONVENIO");
    }

    static Operacion operacion(String numeroOperacion, BigDecimal monto) {
        Operacion operacion = new Operacion();
        operacion.numeroOperacion = numeroOperacion;
        operacion.idMonedaOperacion = 1;
        operacion.montoOperacion = monto;
        operacion.opcPdf = true;
        operacion.opcDevolverCodbarras = true;
        operacion.detalleOperacion = List.of(
                new DetalleOperacion(1, "Item unico", monto, 1)
        );

        operacion.pagador = pagador();

        return operacion;
    }

    private static Pagador pagador() {
        Pagador pagador = new Pagador();
        pagador.emailPagador = "test@example.com";
        pagador.identificacionPagador = new IdentificacionPagador(
                1,
                31252804L,
                20312528046L
        );
        return pagador;
    }

    private static String requireEnv(String name) {
        String value = env(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Debe configurar " + name + " como variable de entorno o en un archivo .env.");
        }
        return value;
    }

    private static String env(String name) {
        return DotEnv.get(name);
    }

    private static Integer integerEnv(String name) {
        return Integer.parseInt(requireEnv(name));
    }

}


