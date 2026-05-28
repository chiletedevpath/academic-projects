package com.demo.sunat.repository;

import com.demo.sunat.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByRucConsultadoOrderByCreatedAtDesc(String rucConsultado);

    Optional<Consulta> findTopByRucConsultadoOrderByCreatedAtDesc(String rucConsultado);
}
