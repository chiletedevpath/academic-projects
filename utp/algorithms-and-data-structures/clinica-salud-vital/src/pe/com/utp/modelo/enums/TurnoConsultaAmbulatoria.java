package pe.com.utp.modelo.enums;

public enum TurnoConsultaAmbulatoria {
    MAÑANA("08:00 - 13:00"),
    TARDE("13:00 - 19:00");

    private final String rango;
    TurnoConsultaAmbulatoria(String rango){
        this.rango = rango;
    }

    public String getRango() {
        return rango;
    }
}
