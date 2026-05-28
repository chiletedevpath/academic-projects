package com.demo.sunat.config;

import com.demo.sunat.dto.ProviderErrorResponse;
import com.demo.sunat.exception.ProviderException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {

        int status = response.status();

        try {

            if (status == 401) {
                return new ProviderException(
                        "Token inválido o expirado al consultar el proveedor",
                        401
                );
            }

            if (status >= 500) {
                return new ProviderException(
                        "El proveedor no se encuentra disponible",
                        status
                );
            }

            if (response.body() != null) {

                InputStream bodyIs = response.body().asInputStream();

                ProviderErrorResponse error =
                        objectMapper.readValue(bodyIs, ProviderErrorResponse.class);

                return new ProviderException(
                        error.message(),
                        status
                );
            }

            return new ProviderException(
                    "Error desconocido del proveedor",
                    status
            );

        } catch (Exception e) {

            return new ProviderException(
                    "Error al procesar la respuesta del proveedor",
                    status
            );
        }
    }
}