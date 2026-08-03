package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credenciales {
    @JsonProperty("hash")
    public String hash;

    @JsonProperty("password")
    public String password;

    @JsonProperty("id_organismo")
    public Integer idOrganismo;

    @JsonProperty("id_usuario")
    public Integer idUsuario;

    public Credenciales() {
    }

    public Credenciales(String hash, String password, Integer idOrganismo, Integer idUsuario) {
        this.hash = hash;
        this.password = password;
        this.idOrganismo = idOrganismo;
        this.idUsuario = idUsuario;
    }
}
