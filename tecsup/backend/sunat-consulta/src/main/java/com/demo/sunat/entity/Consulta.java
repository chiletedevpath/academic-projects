package com.demo.sunat.entity;

import com.demo.sunat.enums.ResultadoConsulta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String rucConsultado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultadoConsulta resultado;

    private String mensajeError;

    private Integer providerStatusCode;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = true)
    @JoinColumn(name = "company_id")
    private Company company;

    public Long getId() {
        return id;
    }

    public String getRucConsultado() {
        return rucConsultado;
    }

    public ResultadoConsulta getResultado() {
        return resultado;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public Integer getProviderStatusCode() {
        return providerStatusCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRucConsultado(String rucConsultado) {
        this.rucConsultado = rucConsultado;
    }

    public void setResultado(ResultadoConsulta resultado) {
        this.resultado = resultado;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public void setProviderStatusCode(Integer providerStatusCode) {
        this.providerStatusCode = providerStatusCode;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
