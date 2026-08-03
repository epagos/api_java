package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagador {
    @JsonProperty("nombre_pagador")
    public String nombrePagador;

    @JsonProperty("apellido_pagador")
    public String apellidoPagador;

    @JsonProperty("fechanac_pagador")
    public String fechanacPagador;

    @JsonProperty("email_pagador")
    public String emailPagador;

    @JsonProperty("identificacion_pagador")
    public IdentificacionPagador identificacionPagador;

    @JsonProperty("domicilio_pagador")
    public DireccionPagador domicilioPagador;

    @JsonProperty("telefono_pagador")
    public TelefonoPagador telefonoPagador;

    @JsonProperty("cbu_pagador")
    public String cbuPagador;
}
