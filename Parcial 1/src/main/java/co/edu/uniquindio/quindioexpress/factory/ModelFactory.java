package co.edu.uniquindio.quindioexpress.factory;

import co.edu.uniquindio.quindioexpress.model.Paquete;
import co.edu.uniquindio.quindioexpress.model.QuindioExpress;
import co.edu.uniquindio.quindioexpress.model.Repartidor;
import co.edu.uniquindio.quindioexpress.model.TipoDespacho;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

public class ModelFactory {
    private static ModelFactory instance;
    private QuindioExpress quindioExpress;

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    private ModelFactory() {
        quindioExpress = inicializarDatos();
    }

    private QuindioExpress inicializarDatos() {
        QuindioExpress empresa = new QuindioExpress();
        // REPARTIDORES
        empresa.add(new Repartidor("R001", "Carlos Ramírez", "Armenia", true));
        empresa.add(new Repartidor("R002", "Laura Gómez", "Salento", true));
        empresa.add(new Repartidor("R003", "Andrés Martínez", "Circasia", true));
        empresa.add(new Repartidor("R004", "Valentina López", "Montenegro", true));
        empresa.add(new Repartidor("R005", "Daniel Torres", "La Tebaida", true));
        empresa.add(new Repartidor("R006", "Camila Herrera", "Armenia", true));

        // PAQUETES - ARMENIA
        empresa.add(new Paquete("PQ731", "Armenia", 4.5, 5, 45));
        empresa.add(new Paquete("PQ105", "Armenia", 2.3, 5, 25));
        empresa.add(new Paquete("PQ842", "Armenia", 8.7, 3, 60));
        empresa.add(new Paquete("PQ318", "Armenia", 1.5, 2, 20));
        empresa.add(new Paquete("PQ967", "Armenia", 12.4, 4, 50));
        empresa.add(new Paquete("PQ214", "Armenia", 3.8, 1, 35));

        // PAQUETES - SALENTO
        empresa.add(new Paquete("PQ420", "Salento", 5.2, 3, 40));
        empresa.add(new Paquete("PQ942", "Salento", 7.1, 4, 20));
        empresa.add(new Paquete("PQ567", "Salento", 2.8, 5, 30));
        empresa.add(new Paquete("PQ153", "Salento", 9.6, 2, 55));
        empresa.add(new Paquete("PQ806", "Salento", 1.9, 1, 25));
        empresa.add(new Paquete("PQ374", "Salento", 6.3, 4, 45));

        // PAQUETES - CIRCASIA
        empresa.add(new Paquete("PQ629", "Circasia", 3.4, 2, 30));
        empresa.add(new Paquete("PQ281", "Circasia", 11.2, 5, 50));
        empresa.add(new Paquete("PQ754", "Circasia", 4.7, 3, 35));
        empresa.add(new Paquete("PQ196", "Circasia", 2.1, 4, 20));
        empresa.add(new Paquete("PQ835", "Circasia", 15.6, 1, 65));
        empresa.add(new Paquete("PQ462", "Circasia", 5.9, 5, 40));

        // PAQUETES - MONTENEGRO
        empresa.add(new Paquete("PQ517", "Montenegro", 6.8, 4, 35));
        empresa.add(new Paquete("PQ903", "Montenegro", 3.2, 2, 25));
        empresa.add(new Paquete("PQ246", "Montenegro", 10.5, 5, 55));
        empresa.add(new Paquete("PQ681", "Montenegro", 1.7, 3, 20));
        empresa.add(new Paquete("PQ359", "Montenegro", 7.4, 1, 45));
        empresa.add(new Paquete("PQ728", "Montenegro", 4.1, 4, 30));

        // PAQUETES - LA TEBAIDA
        empresa.add(new Paquete("PQ594", "La Tebaida", 8.3, 3, 45));
        empresa.add(new Paquete("PQ137", "La Tebaida", 2.6, 5, 25));
        empresa.add(new Paquete("PQ865", "La Tebaida", 13.7, 2, 60));
        empresa.add(new Paquete("PQ403", "La Tebaida", 5.5, 4, 35));
        empresa.add(new Paquete("PQ972", "La Tebaida", 3.9, 1, 30));
        empresa.add(new Paquete("PQ326", "La Tebaida", 9.1, 5, 50));

        return empresa;
    }

    public boolean add(Paquete paquete) {
        return quindioExpress.add(paquete);
    }

    public boolean add(Repartidor repartidor) {
        return quindioExpress.add(repartidor);
    }

    public Repartidor getRepartidorByIdentificacion(String identificacion) {
        return quindioExpress.getRepartidorByIdentificacion(identificacion);
    }

    public Paquete getByCodigo(String codigo) {
        return quindioExpress.getByCodigo(codigo);
    }

    public SortedSet<String> getMunicipios() {
        return quindioExpress.getMunicipios();
    }

    public List<Paquete> getPaquetesByDestino(String destino) {
        return quindioExpress.getPaquetesByDestino(destino);
    }

    public boolean dispatchPaquete(TipoDespacho tipoDespacho) {
        return quindioExpress.dispatchPaquete(tipoDespacho);
    }

    public double getPesoTotalByMunicipio(String municipio) {
        return quindioExpress.getPesoTotalByMunicipio(municipio);
    }

    public int countPaquetesByPrioridad(int prioridadMinima) {
        return quindioExpress.countPaquetesByPrioridad(prioridadMinima);
    }

    public Paquete binarySearchByCodigo(String codigo) {
        return quindioExpress.binarySearchByCodigo(codigo);
    }

    public Paquete getPaqueteMayorPeso() {
        return quindioExpress.getPaqueteMayorPeso();
    }

    public List<Paquete> getPaquetesOrdenados(Comparator<Paquete> comparator) {
        return quindioExpress.getPaquetesOrdenados(comparator);
    }
}
