package com.demo.sunat.dto;

public record SunatRucResponse(String razon_social,
                               String numero_documento,
                               String estado,
                               String condicion,
                               String direccion,
                               String ubigeo,
                               String distrito,
                               String provincia,
                               String departamento,
                               Boolean es_agente_retencion,
                               Boolean es_buen_contribuyente) {




}
