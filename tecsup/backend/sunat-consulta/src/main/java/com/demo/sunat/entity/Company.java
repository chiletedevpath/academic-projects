package com.demo.sunat.entity;

import com.demo.sunat.enums.CondicionDomicilio;
import com.demo.sunat.enums.EstadoContribuyente;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "company",
        uniqueConstraints = @UniqueConstraint(columnNames = "ruc")
)

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ruc debe tener solo 11 dig.
    @Column(nullable = false, unique = true, length = 11)
    private String ruc;

    @Column(nullable = false)
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    private EstadoContribuyente estado;

    @Enumerated(EnumType.STRING)
    private CondicionDomicilio condicion;

    private String direccion;
    private String ubigeo;
    private String departamento;
    private String provincia;
    private String distrito;

    private boolean agenteRetencion;
    private boolean buenContribuyente;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "company")
    private List<Consulta> consultas;

    public Long getId() {
        return id;
    }

    public String getRuc() {
        return ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public EstadoContribuyente getEstado() {
        return estado;
    }

    public CondicionDomicilio getCondicion() {
        return condicion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public boolean isAgenteRetencion() {
        return agenteRetencion;
    }

    public boolean isBuenContribuyente() {
        return buenContribuyente;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setEstado(EstadoContribuyente estado) {
        this.estado = estado;
    }

    public void setCondicion(CondicionDomicilio condicion) {
        this.condicion = condicion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setAgenteRetencion(boolean agenteRetencion) {
        this.agenteRetencion = agenteRetencion;
    }

    public void setBuenContribuyente(boolean buenContribuyente) {
        this.buenContribuyente = buenContribuyente;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}