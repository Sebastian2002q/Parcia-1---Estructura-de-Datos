package co.edu.uniquindio.quindioexpress.comparator;

import co.edu.uniquindio.quindioexpress.model.Paquete;

import java.util.Comparator;

public class PaquetePesoComparator implements Comparator<Paquete> {

    @Override
    public int compare(Paquete p1, Paquete p2) {
        return Double.compare(p2.getPeso(), p1.getPeso());
    }
}