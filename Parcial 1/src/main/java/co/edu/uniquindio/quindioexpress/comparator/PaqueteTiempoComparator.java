package co.edu.uniquindio.quindioexpress.comparator;

import co.edu.uniquindio.quindioexpress.model.Paquete;

import java.util.Comparator;

public class PaqueteTiempoComparator implements Comparator<Paquete> {

    @Override
    public int compare(Paquete p1, Paquete p2) {
        return Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado());
    }
}
