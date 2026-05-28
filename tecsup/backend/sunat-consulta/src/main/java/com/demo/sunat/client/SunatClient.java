package com.demo.sunat.client;

import com.demo.sunat.dto.SunatRucResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sunatClient",
        url = "${decolecta.base-url}",
        configuration = com.demo.sunat.config.FeignConfig.class
)
public interface SunatClient {

    @GetMapping("/v1/sunat/ruc")
    SunatRucResponse consultaRuc(@RequestParam("numero") String numero);


}
