package co.edu.uniquindio.quindioexpress.controller;

import co.edu.uniquindio.quindioexpress.factory.ModelFactory;
import co.edu.uniquindio.quindioexpress.model.Paquete;
import co.edu.uniquindio.quindioexpress.model.Repartidor;

import java.util.List;

public class ConsoleController {
    private ModelFactory modelFactory;

    public ConsoleController() {
        modelFactory = ModelFactory.getInstance();
    }

    public boolean add(Paquete paquete) {
        return modelFactory.add(paquete);
    }

    public boolean add(Repartidor repartidor) {
        return modelFactory.add(repartidor);
    }

    public Paquete getByCodigo(String codigo) {
        return modelFactory.getByCodigo(codigo);
    }

    public List<String> getMunicipios() {
        return modelFactory.getMunicipios();
    }
}
