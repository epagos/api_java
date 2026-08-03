package com.epagos.sdk;

public final class EpagosResult<REQ, RES extends EpagosResponse> {
    private final REQ request;
    private final RES response;
    private final int httpStatus;
    private final String rawBody;

    public EpagosResult(REQ request, RES response, int httpStatus, String rawBody) {
        this.request = request;
        this.response = response;
        this.httpStatus = httpStatus;
        this.rawBody = rawBody;
    }

    public REQ getRequest() {
        return request;
    }

    public RES getResponse() {
        return response;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getRawBody() {
        return rawBody;
    }

    public boolean isSuccessful() {
        return response != null && response.isSuccessfulResponse();
    }
}
