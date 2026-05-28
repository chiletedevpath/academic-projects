package com.demo.sunat.mapper;

import com.demo.sunat.dto.CompanyResponse;
import com.demo.sunat.dto.ConsultaResponse;
import com.demo.sunat.entity.Company;
import com.demo.sunat.entity.Consulta;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    private CompanyMapper() {
    }

    public static CompanyResponse toResponse(Company company, List<Consulta> consultas) {

        List<ConsultaResponse> historial = consultas.stream()
                .map(c -> new ConsultaResponse(
                        c.getRucConsultado(),
                        c.getResultado(),
                        c.getMensajeError(),
                        c.getProviderStatusCode(),
                        c.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return new CompanyResponse(
                company.getRuc(),
                company.getRazonSocial(),
                company.getEstado(),
                company.getCondicion(),
                company.getDireccion(),
                company.getDepartamento(),
                company.getProvincia(),
                company.getDistrito(),
                company.isAgenteRetencion(),
                company.isBuenContribuyente(),
                historial
        );
    }
}