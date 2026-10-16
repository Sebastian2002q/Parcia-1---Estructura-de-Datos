package co.edu.uniquindio.quindioexpress.comparator;

import co.edu.uniquindio.quindioexpress.model.Paquete;

import java.util.Comparator;

public class PaqueteDespachoComparator implements Comparator<Paquete> {

    private final Comparator<Paquete> comparador = new PaquetePrioridadComparator().thenComparing(new PaqueteTiempoComparator()).thenComparing(Paquete::getCodigo);

    @Override
    public int compare(Paquete p1, Paquete p2) {
        return comparador.compare(p1, p2);
    }
}