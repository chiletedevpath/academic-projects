package com.demo.sunat.enums;

public enum CondicionDomicilio {

    HABIDO,
    NO_HABIDO,
    PENDIENTE,
    DESCONOCIDO;

    public static CondicionDomicilio from(String value) {

        if (value == null) {
            return DESCONOCIDO;
        }

        try {
            return CondicionDomicilio.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            return DESCONOCIDO;
        }
    }
}