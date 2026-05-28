package com.demo.sunat.enums;

import java.util.Locale;

public enum EstadoContribuyente {
    ACTIVO,
    BAJA,
    SUSPENDIDO,
    DESCONOCIDO;

    public static EstadoContribuyente from(String value) {
        if (value == null) {
            return DESCONOCIDO;
        }

        try {
            return EstadoContribuyente.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            return DESCONOCIDO;
        }
    }

}
