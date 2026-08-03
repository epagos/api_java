package com.epagos.sdk.internal;

import com.epagos.sdk.EpagosException;
import com.epagos.sdk.EpagosResponse;
import com.epagos.sdk.EpagosResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

public final class RestTransport {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public RestTransport(String baseUrl) {
        this.baseUrl = stripTrailingSlash(baseUrl);
        this.httpClient = HttpClient.newBuilder().connectTimeout(DEFAULT_TIMEOUT).build();
        this.mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public <REQ, RES extends EpagosResponse> EpagosResult<REQ, RES> put(
            String path,
            REQ request,
            Class<RES> responseType
    ) {
        return send("PUT", path, request, responseType);
    }

    public <REQ, RES extends EpagosResponse> EpagosResult<REQ, RES> getWithBody(
            String path,
            REQ request,
            Class<RES> responseType
    ) {
        return send("GET", path, request, responseType);
    }

    private <REQ, RES extends EpagosResponse> EpagosResult<REQ, RES> send(
            String method,
            String path,
            REQ request,
            Class<RES> responseType
    ) {
        String body = toJson(request);
        URI uri = uri(path);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .timeout(DEFAULT_TIMEOUT)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .method(method, HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new EpagosException("La llamada fue interrumpida.", e);
        } catch (IOException e) {
            throw new EpagosException("No se pudo invocar la API.", e);
        }

        int status = httpResponse.statusCode();
        String rawBody = httpResponse.body();

        if (status < 200 || status >= 300) {
            throw new EpagosException(httpErrorMessage(status, httpResponse), status, rawBody);
        }

        RES response = fromJson(rawBody, responseType);
        return new EpagosResult<>(request, response, status, rawBody);
    }

    private String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new EpagosException("No se pudo serializar el request.", e);
        }
    }

    private <RES> RES fromJson(String rawBody, Class<RES> responseType) {
        try {
            return mapper.readValue(rawBody, responseType);
        } catch (JsonProcessingException e) {
            throw new EpagosException("No se pudo deserializar la respuesta", e);
        }
    }

    private URI uri(String path) {
        String normalizedPath = path.startsWith("/") ? path : "/" + path;
        return URI.create(baseUrl + normalizedPath);
    }

    private static String httpErrorMessage(int status, HttpResponse<String> response) {
        StringBuilder message = new StringBuilder("La API devolvio HTTP ")
                .append(status)
                .append(".");

        Optional<String> location = response.headers().firstValue("Location");
        location.ifPresent(value -> message.append(" Location: ").append(value).append("."));

        Optional<String> contentType = response.headers().firstValue("Content-Type");
        contentType.ifPresent(value -> message.append(" Content-Type: ").append(value).append("."));

        return message.toString();
    }

    private static String stripTrailingSlash(String url) {
        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }
}

