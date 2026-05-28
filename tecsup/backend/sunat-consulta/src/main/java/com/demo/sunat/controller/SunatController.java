package com.demo.sunat.controller;

import com.demo.sunat.dto.CompanyResponse;
import com.demo.sunat.dto.ConsultaResponse;
import com.demo.sunat.repository.ConsultaRepository;
import com.demo.sunat.service.SunatService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/sunat")
public class SunatController {

    private final SunatService sunatService;
    private final ConsultaRepository consultaRepository;

    public SunatController(
            SunatService sunatService,
            ConsultaRepository consultaRepository
    ) {
        this.sunatService = sunatService;
        this.consultaRepository = consultaRepository;
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<CompanyResponse> consultarRuc(
            @PathVariable
            @Pattern(regexp = "\\d{11}", message = "El RUC debe tener exactamente 11 dígitos")
            String ruc
    ) {
        return ResponseEntity.ok(
                sunatService.consultarRuc(ruc)
        );
    }

    @GetMapping("/ruc/{ruc}/consultas")
    public ResponseEntity<List<ConsultaResponse>> historial(
            @PathVariable
            @Pattern(regexp = "\\d{11}", message = "El RUC debe tener exactamente 11 dígitos")
            String ruc
    ) {

        List<ConsultaResponse> historial =
                consultaRepository
                        .findByRucConsultadoOrderByCreatedAtDesc(ruc)
                        .stream()
                        .map(c -> new ConsultaResponse(
                                c.getRucConsultado(),
                                c.getResultado(),
                                c.getMensajeError(),
                                c.getProviderStatusCode(),
                                c.getCreatedAt()
                        ))
                        .toList();

        return ResponseEntity.ok(historial);
    }
}