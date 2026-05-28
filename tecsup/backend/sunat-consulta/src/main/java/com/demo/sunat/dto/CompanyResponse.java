package com.demo.sunat.dto;

import com.demo.sunat.enums.CondicionDomicilio;
import com.demo.sunat.enums.EstadoContribuyente;

import java.util.List;

public record CompanyResponse(String ruc,
                              String razonSocial,
                              EstadoContribuyente estado,
                              CondicionDomicilio condicion,
                              String direccion,
                              String departamento,
                              String provincia,
                              String distrito,
                              boolean agenteRetencion,
                              boolean buenContribuyente,
                              List<ConsultaResponse> consultas) {
}
