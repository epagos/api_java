package com.epagos.sdk;

public interface EpagosResponse {
    Integer getIdResp();

    default boolean isSuccessfulResponse() {
        Integer idResp = getIdResp();
        return idResp != null
                && (idResp == 1001
                || idResp == 2001
                || idResp == 2002
                || idResp == 3001
                || idResp == 4001
                || idResp == 8001
                || idResp == 15001
                || idResp == 18001
                || idResp == 18002);
    }
}
