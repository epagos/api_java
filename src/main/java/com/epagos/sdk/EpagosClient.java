package com.epagos.sdk;

import com.epagos.sdk.internal.RestTransport;
import com.epagos.sdk.model.Credenciales;
import com.epagos.sdk.model.FiltroObtenerContracargos;
import com.epagos.sdk.model.FiltroObtenerPagos;
import com.epagos.sdk.model.FiltroObtenerRendiciones;
import com.epagos.sdk.model.FiltroPagosAdicionales;
import com.epagos.sdk.model.FormaPago;
import com.epagos.sdk.model.GenerarOrdenQrRequest;
import com.epagos.sdk.model.GenerarOrdenQrResponse;
import com.epagos.sdk.model.ObtenerContracargosRequest;
import com.epagos.sdk.model.ObtenerContracargosResponse;
import com.epagos.sdk.model.ObtenerCajasQrRequest;
import com.epagos.sdk.model.ObtenerCajasQrResponse;
import com.epagos.sdk.model.ObtenerEntidadesPagoRequest;
import com.epagos.sdk.model.ObtenerEntidadesPagoResponse;
import com.epagos.sdk.model.ObtenerPagosAdicionalesRequest;
import com.epagos.sdk.model.ObtenerPagosAdicionalesResponse;
import com.epagos.sdk.model.ObtenerPagosRequest;
import com.epagos.sdk.model.ObtenerPagosResponse;
import com.epagos.sdk.model.ObtenerRendicionesRequest;
import com.epagos.sdk.model.ObtenerRendicionesResponse;
import com.epagos.sdk.model.Operacion;
import com.epagos.sdk.model.OperacionPagoLote;
import com.epagos.sdk.model.OrdenQr;
import com.epagos.sdk.model.PagoLote;
import com.epagos.sdk.model.PagoLoteRequest;
import com.epagos.sdk.model.PagoLoteResponse;
import com.epagos.sdk.model.SolicitudLoteItem;
import com.epagos.sdk.model.SolicitudPagoLoteRequest;
import com.epagos.sdk.model.SolicitudPagoLoteResponse;
import com.epagos.sdk.model.SolicitudPagoRequest;
import com.epagos.sdk.model.SolicitudPagoResponse;
import com.epagos.sdk.model.TokenResponse;

import java.util.List;

public final class EpagosClient {
    public static final String SANDBOX_BASE_URL = "https://sandbox.epagos.com/v1.0";
    public static final String PRODUCTION_BASE_URL = "https://api.epagos.com/v1.0";

    private final RestTransport transport;

    private EpagosClient(String baseUrl) {
        this.transport = new RestTransport(baseUrl);
    }

    public static EpagosClient sandbox() {
        return new EpagosClient(SANDBOX_BASE_URL);
    }

    public static EpagosClient production() {
        return new EpagosClient(PRODUCTION_BASE_URL);
    }

    public EpagosResult<Credenciales, TokenResponse> obtenerToken(Credenciales credenciales) {
        requireCredentials(credenciales);
        return transport.put("/token", credenciales, TokenResponse.class);
    }

    public EpagosResult<ObtenerPagosRequest, ObtenerPagosResponse> obtenerPagos(
            Credenciales credenciales,
            FiltroObtenerPagos filtro
    ) {
        requireCredentials(credenciales);
        String token = getToken(credenciales);
        ObtenerPagosRequest request = new ObtenerPagosRequest(
                credenciales.idOrganismo,
                token,
                filtro == null ? new FiltroObtenerPagos() : filtro
        );

        return transport.getWithBody("/transaccion", request, ObtenerPagosResponse.class);
    }

    public EpagosResult<ObtenerPagosRequest, ObtenerPagosResponse> obtenerPagos(ObtenerPagosRequest request) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        if (request.pago == null) {
            request.pago = new FiltroObtenerPagos();
        }

        return transport.getWithBody("/transaccion", request, ObtenerPagosResponse.class);
    }

    public EpagosResult<ObtenerPagosRequest, ObtenerPagosResponse> obtenerDevoluciones(
            Credenciales credenciales,
            FiltroObtenerPagos filtro
    ) {
        FiltroObtenerPagos devoluciones = filtro == null ? new FiltroObtenerPagos() : filtro.copy();
        devoluciones.estado = "D";
        return obtenerPagos(credenciales, devoluciones);
    }

    public EpagosResult<ObtenerContracargosRequest, ObtenerContracargosResponse> obtenerContracargos(
            Credenciales credenciales,
            FiltroObtenerContracargos filtro
    ) {
        requireCredentials(credenciales);
        requireArgument("filtro", filtro);
        String token = getToken(credenciales);
        ObtenerContracargosRequest request = new ObtenerContracargosRequest(
                credenciales.idOrganismo,
                token,
                filtro
        );

        return transport.getWithBody("/contracargo", request, ObtenerContracargosResponse.class);
    }

    public EpagosResult<ObtenerContracargosRequest, ObtenerContracargosResponse> obtenerContracargos(
            ObtenerContracargosRequest request
    ) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        requireArgument("request.contracargos", request.contracargos);

        return transport.getWithBody("/contracargo", request, ObtenerContracargosResponse.class);
    }

    public EpagosResult<ObtenerPagosAdicionalesRequest, ObtenerPagosAdicionalesResponse> obtenerPagosAdicionales(
            Credenciales credenciales,
            FiltroPagosAdicionales filtro
    ) {
        requireCredentials(credenciales);
        requireArgument("filtro", filtro);
        String token = getToken(credenciales);
        ObtenerPagosAdicionalesRequest request = new ObtenerPagosAdicionalesRequest(
                credenciales.idOrganismo,
                token,
                filtro
        );

        return transport.getWithBody("/pagoAdicional", request, ObtenerPagosAdicionalesResponse.class);
    }

    public EpagosResult<ObtenerPagosAdicionalesRequest, ObtenerPagosAdicionalesResponse> obtenerPagosAdicionales(
            ObtenerPagosAdicionalesRequest request
    ) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        requireArgument("request.pagos", request.pagos);

        return transport.getWithBody("/pagoAdicional", request, ObtenerPagosAdicionalesResponse.class);
    }

    public EpagosResult<ObtenerRendicionesRequest, ObtenerRendicionesResponse> obtenerRendiciones(
            Credenciales credenciales,
            FiltroObtenerRendiciones filtro
    ) {
        requireCredentials(credenciales);
        requireArgument("filtro", filtro);
        String token = getToken(credenciales);
        ObtenerRendicionesRequest request = new ObtenerRendicionesRequest(
                credenciales.idOrganismo,
                token,
                filtro
        );

        return transport.getWithBody("/rendicion", request, ObtenerRendicionesResponse.class);
    }

    public EpagosResult<ObtenerRendicionesRequest, ObtenerRendicionesResponse> obtenerRendiciones(
            ObtenerRendicionesRequest request
    ) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        requireArgument("request.rendicion", request.rendicion);

        return transport.getWithBody("/rendicion", request, ObtenerRendicionesResponse.class);
    }

    public EpagosResult<SolicitudPagoRequest, SolicitudPagoResponse> solicitudPagos(
            Credenciales credenciales,
            Operacion operacion,
            List<FormaPago> formasPago,
            int convenio
    ) {
        requireCredentials(credenciales);
        requireArgument("operacion", operacion);
        requireNonEmpty("formasPago", formasPago);

        String token = getToken(credenciales);
        SolicitudPagoRequest request = new SolicitudPagoRequest(
                credenciales.idOrganismo,
                token,
                convenio,
                operacion,
                formasPago
        );

        return transport.put("/transaccion", request, SolicitudPagoResponse.class);
    }

    public EpagosResult<SolicitudPagoRequest, SolicitudPagoResponse> solicitudPagos(SolicitudPagoRequest request) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        requireArgument("request.convenio", request.convenio);
        requireArgument("request.operacion", request.operacion);
        requireNonEmpty("request.fp", request.fp);

        return transport.put("/transaccion", request, SolicitudPagoResponse.class);
    }

    public EpagosResult<SolicitudPagoLoteRequest, SolicitudPagoLoteResponse> solicitudPagosLote(
            Credenciales credenciales,
            List<SolicitudLoteItem> lote
    ) {
        requireCredentials(credenciales);
        requireNonEmpty("lote", lote);
        if (lote.size() > 50) {
            throw new IllegalArgumentException("El lote no puede superar 50 operaciones.");
        }

        String token = getToken(credenciales);
        SolicitudPagoLoteRequest request = new SolicitudPagoLoteRequest(
                credenciales.idOrganismo,
                token,
                lote
        );

        return transport.put("/transaccionLote", request, SolicitudPagoLoteResponse.class);
    }

    public EpagosResult<SolicitudPagoLoteRequest, SolicitudPagoLoteResponse> solicitudPagosLote(SolicitudPagoLoteRequest request) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        requireNonEmpty("request.lote", request.lote);
        if (request.lote.size() > 50) {
            throw new IllegalArgumentException("El lote no puede superar 50 operaciones.");
        }

        return transport.put("/transaccionLote", request, SolicitudPagoLoteResponse.class);
    }

    public EpagosResult<PagoLoteRequest, PagoLoteResponse> pagoLote(
            Credenciales credenciales,
            PagoLote pagoLote
    ) {
        requireCredentials(credenciales);
        requirePagoLote(pagoLote);

        String token = getToken(credenciales);
        PagoLoteRequest request = new PagoLoteRequest(
                credenciales.idOrganismo,
                token,
                pagoLote
        );

        return transport.put("/pagoLote", request, PagoLoteResponse.class);
    }

    public EpagosResult<PagoLoteRequest, PagoLoteResponse> pagoLote(PagoLoteRequest request) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        requirePagoLote(request.pagoLote);

        return transport.put("/pagoLote", request, PagoLoteResponse.class);
    }

    public EpagosResult<ObtenerEntidadesPagoRequest, ObtenerEntidadesPagoResponse> obtenerEntidadesPago(
            Credenciales credenciales
    ) {
        return obtenerEntidadesPago(credenciales, null);
    }

    public EpagosResult<ObtenerEntidadesPagoRequest, ObtenerEntidadesPagoResponse> obtenerEntidadesPago(
            Credenciales credenciales,
            List<Integer> fp
    ) {
        requireCredentials(credenciales);

        String token = getToken(credenciales);
        ObtenerEntidadesPagoRequest request = new ObtenerEntidadesPagoRequest(
                credenciales.idOrganismo,
                token,
                fp
        );

        return transport.getWithBody("/entidadPago", request, ObtenerEntidadesPagoResponse.class);
    }

    public EpagosResult<ObtenerEntidadesPagoRequest, ObtenerEntidadesPagoResponse> obtenerEntidadesPago(
            ObtenerEntidadesPagoRequest request
    ) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);

        return transport.getWithBody("/entidadPago", request, ObtenerEntidadesPagoResponse.class);
    }

    public EpagosResult<GenerarOrdenQrRequest, GenerarOrdenQrResponse> generarOrdenQr(
            Credenciales credenciales,
            OrdenQr orden
    ) {
        requireCredentials(credenciales);
        requireOrdenQr(orden);

        String token = getToken(credenciales);
        GenerarOrdenQrRequest request = new GenerarOrdenQrRequest(
                credenciales.idOrganismo,
                token,
                orden
        );

        return transport.put("/ordenQR", request, GenerarOrdenQrResponse.class);
    }

    public EpagosResult<GenerarOrdenQrRequest, GenerarOrdenQrResponse> generarOrdenQr(
            GenerarOrdenQrRequest request
    ) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);
        requireOrdenQr(request.orden);

        return transport.put("/ordenQR", request, GenerarOrdenQrResponse.class);
    }

    public EpagosResult<ObtenerCajasQrRequest, ObtenerCajasQrResponse> obtenerCajasQr(
            Credenciales credenciales
    ) {
        requireCredentials(credenciales);

        String token = getToken(credenciales);
        ObtenerCajasQrRequest request = new ObtenerCajasQrRequest(
                credenciales.idOrganismo,
                token
        );

        return transport.getWithBody("/cajaQR", request, ObtenerCajasQrResponse.class);
    }

    public EpagosResult<ObtenerCajasQrRequest, ObtenerCajasQrResponse> obtenerCajasQr(
            ObtenerCajasQrRequest request
    ) {
        requireArgument("request", request);
        requireArgument("request.idOrganismo", request.idOrganismo);
        requireArgument("request.token", request.token);

        return transport.getWithBody("/cajaQR", request, ObtenerCajasQrResponse.class);
    }

    private String getToken(Credenciales credenciales) {
        EpagosResult<Credenciales, TokenResponse> tokenResult = obtenerToken(credenciales);
        TokenResponse response = tokenResult.getResponse();
        if (response == null || response.token == null || response.token.trim().isEmpty()) {
            throw new EpagosException("No se pudo obtener el token de ePagos.");
        }
        return response.token.trim();
    }

    private static void requireCredentials(Credenciales credenciales) {
        requireArgument("credenciales", credenciales);
        requireArgument("credenciales.hash", credenciales.hash);
        requireArgument("credenciales.password", credenciales.password);
        requireArgument("credenciales.idOrganismo", credenciales.idOrganismo);
        requireArgument("credenciales.idUsuario", credenciales.idUsuario);
    }

    private static void requireArgument(String name, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Debe indicar " + name + ".");
        }
        if (value instanceof String text && text.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar " + name + ".");
        }
    }

    private static void requirePagoLote(PagoLote pagoLote) {
        requireArgument("pagoLote", pagoLote);
        requireArgument("pagoLote.idTransaccion", pagoLote.idTransaccion);
        requireArgument("pagoLote.formaPago", pagoLote.formaPago);
        requireArgument("pagoLote.fechaPago", pagoLote.fechaPago);
        requireArgument("pagoLote.importe", pagoLote.importe);
        requireNonEmpty("pagoLote.operaciones", pagoLote.operaciones);
        for (OperacionPagoLote operacion : pagoLote.operaciones) {
            requireArgument("pagoLote.operaciones[].idTransaccion", operacion == null ? null : operacion.idTransaccion);
            requireArgument("pagoLote.operaciones[].importe", operacion == null ? null : operacion.importe);
        }
    }

    private static void requireOrdenQr(OrdenQr orden) {
        requireArgument("orden", orden);
        requireArgument("orden.importe", orden.importe);
        if (orden.idCaja == null && orden.idTransaccion == null) {
            throw new IllegalArgumentException("Debe indicar orden.idCaja u orden.idTransaccion.");
        }
    }

    private static void requireNonEmpty(String name, List<?> value) {
        requireArgument(name, value);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Debe indicar " + name + ".");
        }
    }
}
