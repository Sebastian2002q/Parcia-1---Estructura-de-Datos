package co.edu.uniquindio.quindioexpress.model;

public class Paquete implements Comparable<Paquete> {
    private final String codigo;
    private final String destino;
    private final double peso;
    private final int prioridad;
    private final int tiempoEstimado;

    public Paquete(String codigo, String destino, double peso, int prioridad, int tiempoEstimado) {
        //Validaciones
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("El destino no puede estar vacio");
        }
        if (!Double.isFinite(peso) || peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5");
        }
        if (tiempoEstimado <= 0) {
            throw new IllegalArgumentException("El tiempo estimado debe ser mayor que cero");
        }
        //Construccion
        this.codigo = codigo.trim();
        this.destino = destino.trim();
        this.peso = peso;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    @Override
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return "Paquete{" + "codigo='" + codigo + '\'' + ", destino='" + destino + '\'' + ", peso=" + peso + ", prioridad=" + prioridad + ", tiempoEstimado=" + tiempoEstimado + '}';
    }
}