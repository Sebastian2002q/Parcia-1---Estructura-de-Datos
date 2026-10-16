package co.edu.uniquindio.quindioexpress.model;

import co.edu.uniquindio.quindioexpress.comparator.PaqueteDespachoComparator;

import java.util.*;

public class QuindioExpress {

    private final ArrayList<Paquete> paquetes;
    private final HashMap<String, Paquete> paquetesPorCodigo;
    private final TreeSet<String> municipios;
    private final HashMap<String, List<Paquete>> paquetesPorDestino;
    private final PriorityQueue<Paquete> colaPrioridad;
    private final HashMap<String, Repartidor> repartidores;

    public QuindioExpress() {
        paquetes = new ArrayList<>();
        paquetesPorCodigo = new HashMap<>();
        municipios = new TreeSet<>();
        paquetesPorDestino = new HashMap<>();
        colaPrioridad = new PriorityQueue<>(new PaqueteDespachoComparator());
        repartidores = new HashMap<>();
    }

    public boolean add(Paquete paquete) {
        // Validar que el paquete no sea nulo
        if (paquete == null) {
            return false;
        }
        // Validar que el codigo sea unico
        if (paquetesPorCodigo.containsKey(paquete.getCodigo())) {
            return false;
        }
        // Conservar el orden de registro
        paquetes.add(paquete);
        // Permitir busqueda rapida por codigo
        paquetesPorCodigo.put(paquete.getCodigo(), paquete);
        // Registrar municipios sin duplicados y ordenados
        municipios.add(paquete.getDestino());
        // Agrupar paquetes por destino
        paquetesPorDestino.computeIfAbsent(paquete.getDestino(), destino -> new ArrayList<>()).add(paquete);
        // Agregar a la cola de despacho prioritario
        colaPrioridad.offer(paquete);
        return true;
    }

    public boolean add(Repartidor repartidor) {
        // Validar que el repartidor no sea nulo
        if (repartidor == null) {
            return false;
        }
        // Validar que la identificacion sea unica
        if (repartidores.containsKey(repartidor.getIdentificacion())) {
            return false;
        }
        repartidores.put(repartidor.getIdentificacion(), repartidor);
        return true;
    }

    public Paquete getByCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return null;
        }
        return paquetesPorCodigo.get(codigo.trim());
    }

    public SortedSet<String> getMunicipios() {
        return Collections.unmodifiableSortedSet(municipios);
    }

    public List<Paquete> getPaquetesByDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            return Collections.emptyList();
        }
        List<Paquete> paquetesDestino = paquetesPorDestino.get(destino.trim());
        if (paquetesDestino == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(paquetesDestino);
    }
}