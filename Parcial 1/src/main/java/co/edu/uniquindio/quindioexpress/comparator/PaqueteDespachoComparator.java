package co.edu.uniquindio.quindioexpress.comparator;

import co.edu.uniquindio.quindioexpress.model.Paquete;

import java.util.Comparator;

public class PaqueteDespachoComparator implements Comparator<Paquete> {
    @Override
    public int compare(Paquete p1, Paquete p2) {
        // 1. Prioridad descendente
        int resultado = Integer.compare(p2.getPrioridad(), p1.getPrioridad());
        if (resultado != 0) {
            return resultado;
        }

        // 2. Tiempo estimado ascendente
        resultado = Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado());
        if (resultado != 0) {
            return resultado;
        }

        // 3. Código ascendente
        return p1.getCodigo().compareTo(p2.getCodigo());
    }
}