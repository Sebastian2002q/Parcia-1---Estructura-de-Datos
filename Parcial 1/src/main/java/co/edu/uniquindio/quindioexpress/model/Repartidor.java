package co.edu.uniquindio.quindioexpress.model;

public class Repartidor {

    private final String identificacion;
    private final String nombreCompleto;
    private final String zona;
    private boolean disponible;

    public Repartidor(String identificacion, String nombreCompleto, String zona, boolean disponible) {
        //Validaciones
        if (identificacion == null || identificacion.isBlank()) {
            throw new IllegalArgumentException("La identificacion no puede estar vacia");
        }
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        }
        if (zona == null || zona.isBlank()) {
            throw new IllegalArgumentException("La zona no puede estar vacia");
        }
        //Construccion
        this.identificacion = identificacion.trim();
        this.nombreCompleto = nombreCompleto.trim();
        this.zona = zona.trim();
        this.disponible = disponible;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getZona() {
        return zona;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Repartidor{" + "identificacion='" + identificacion + '\'' + ", nombreCompleto='" + nombreCompleto + '\'' + ", zona='" + zona + '\'' + ", disponible=" + disponible + '}';
    }
}
