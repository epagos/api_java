package com.epagos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DireccionPagador {
    @JsonProperty("calle_dom_pagador")
    public String calleDomPagador;

    @JsonProperty("numero_dom_pagador")
    public String numeroDomPagador;

    @JsonProperty("adicional_dom_pagador")
    public String adicionalDomPagador;

    @JsonProperty("cp_dom_pagador")
    public String cpDomPagador;

    @JsonProperty("ciudad_dom_pagador")
    public String ciudadDomPagador;

    @JsonProperty("provincia_dom_pagador")
    public Integer provinciaDomPagador;

    @JsonProperty("pais_dom_pagador")
    public Integer paisDomPagador;
}
