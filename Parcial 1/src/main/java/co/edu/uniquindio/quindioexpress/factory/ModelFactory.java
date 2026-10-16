package co.edu.uniquindio.quindioexpress.factory;

import co.edu.uniquindio.quindioexpress.model.Paquete;
import co.edu.uniquindio.quindioexpress.model.QuindioExpress;
import co.edu.uniquindio.quindioexpress.model.Repartidor;

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

    private static QuindioExpress inicializarDatos() {
        QuindioExpress quindioExpress = new QuindioExpress();
        return quindioExpress;
    }

    public boolean add(Paquete paquete) {
        return quindioExpress.add(paquete);
    }

    public boolean add(Repartidor repartidor) {
        return quindioExpress.add(repartidor);
    }

    public Paquete getByCodigo(String codigo){
        return quindioExpress.getByCodigo(codigo);
    }

    public SortedSet<String> getMunicipios() {
        return quindioExpress.getMunicipios();
    }

    public List<Paquete> getPaquetesByDestino(String destino) {
        return quindioExpress.getPaquetesByDestino(destino);
    }
}
