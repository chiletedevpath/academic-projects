package com.demo.sunat.service;

import com.demo.sunat.client.SunatClient;
import com.demo.sunat.dto.CompanyResponse;
import com.demo.sunat.dto.SunatRucResponse;
import com.demo.sunat.entity.Company;
import com.demo.sunat.entity.Consulta;
import com.demo.sunat.enums.CondicionDomicilio;
import com.demo.sunat.enums.EstadoContribuyente;
import com.demo.sunat.enums.ResultadoConsulta;
import com.demo.sunat.exception.ProviderException;
import com.demo.sunat.mapper.CompanyMapper;
import com.demo.sunat.repository.CompanyRepository;
import com.demo.sunat.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SunatService {

    private final SunatClient sunatClient;
    private final CompanyRepository companyRepository;
    private final ConsultaRepository consultaRepository;

    public SunatService(
            SunatClient sunatClient,
            CompanyRepository companyRepository,
            ConsultaRepository consultaRepository
    ) {
        this.sunatClient = sunatClient;
        this.companyRepository = companyRepository;
        this.consultaRepository = consultaRepository;
    }

    @Transactional
    public CompanyResponse consultarRuc(String ruc) {

        Consulta consulta = new Consulta();
        consulta.setRucConsultado(ruc);

        Optional<Company> existingCompany = companyRepository.findByRuc(ruc);
        Optional<Consulta> lastConsulta =
                consultaRepository.findTopByRucConsultadoOrderByCreatedAtDesc(ruc);

        if (existingCompany.isPresent() && lastConsulta.isPresent()) {

            LocalDateTime lastTime = lastConsulta.get().getCreatedAt();

            long minutes = Duration.between(lastTime, LocalDateTime.now()).toMinutes();

            if (minutes < 10) {

                // Registrar nueva consulta como suceess cache
                consulta.setResultado(ResultadoConsulta.SUCCESS);
                consulta.setCompany(existingCompany.get());
                consultaRepository.save(consulta);

                List<Consulta> consultas =
                        consultaRepository
                                .findByRucConsultadoOrderByCreatedAtDesc(ruc);

                return CompanyMapper.toResponse(existingCompany.get(), consultas);
            }
        }

        try {

            SunatRucResponse response =
                    sunatClient.consultaRuc(ruc);

            Company company = companyRepository
                    .findByRuc(ruc)
                    .orElseGet(Company::new);

            mapResponseToCompany(response, company);

            companyRepository.save(company);

            consulta.setResultado(ResultadoConsulta.SUCCESS);
            consulta.setCompany(company);
            consultaRepository.save(consulta);

            List<Consulta> consultas =
                    consultaRepository
                            .findByRucConsultadoOrderByCreatedAtDesc(ruc);

            return CompanyMapper.toResponse(company, consultas);

        } catch (ProviderException ex) {

            consulta.setResultado(ResultadoConsulta.ERROR);
            consulta.setMensajeError(ex.getMessage());
            consulta.setProviderStatusCode(ex.getStatus());
            consultaRepository.save(consulta);

            throw ex;

        } catch (Exception ex) {

            consulta.setResultado(ResultadoConsulta.ERROR);
            consulta.setMensajeError("Unexpected error");
            consultaRepository.save(consulta);

            throw ex;
        }
    }

    private void mapResponseToCompany(
            SunatRucResponse response,
            Company company
    ) {
        company.setRuc(response.numero_documento());
        company.setRazonSocial(response.razon_social());
        company.setEstado(
                EstadoContribuyente.from(response.estado())
        );
        company.setCondicion(
                CondicionDomicilio.from(response.condicion())
        );
        company.setDireccion(response.direccion());
        company.setUbigeo(response.ubigeo());
        company.setDepartamento(response.departamento());
        company.setProvincia(response.provincia());
        company.setDistrito(response.distrito());
        company.setAgenteRetencion(
                Boolean.TRUE.equals(response.es_agente_retencion())
        );
        company.setBuenContribuyente(
                Boolean.TRUE.equals(response.es_buen_contribuyente())
        );
    }
}