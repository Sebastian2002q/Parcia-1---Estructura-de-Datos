package co.edu.uniquindio.quindioexpress.controller;

import co.edu.uniquindio.quindioexpress.factory.ModelFactory;
import co.edu.uniquindio.quindioexpress.model.Paquete;
import co.edu.uniquindio.quindioexpress.model.Repartidor;
import co.edu.uniquindio.quindioexpress.model.TipoDespacho;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

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

    public Repartidor getRepartidorByIdentificacion(String identificacion) {
        return modelFactory.getRepartidorByIdentificacion(identificacion);
    }

    public Paquete getByCodigo(String codigo) {
        return modelFactory.getByCodigo(codigo);
    }

    public SortedSet<String> getMunicipios() {
        return modelFactory.getMunicipios();
    }

    public List<Paquete> getPaquetesByDestino(String destino) {
        return modelFactory.getPaquetesByDestino(destino);
    }

    public boolean dispatchPaquete(TipoDespacho tipoDespacho) {
        return modelFactory.dispatchPaquete(tipoDespacho);
    }

    public double getPesoTotalByMunicipio(String municipio) {
        return modelFactory.getPesoTotalByMunicipio(municipio);
    }

    public int countPaquetesByPrioridad(int prioridadMinima) {
        return modelFactory.countPaquetesByPrioridad(prioridadMinima);
    }

    public Paquete binarySearchByCodigo(String codigo) {
        return modelFactory.binarySearchByCodigo(codigo);
    }

    public Paquete getPaqueteMayorPeso() {
        return modelFactory.getPaqueteMayorPeso();
    }

    public List<Paquete> getPaquetesOrdenados(Comparator<Paquete> comparator) {
        return modelFactory.getPaquetesOrdenados(comparator);
    }
}
