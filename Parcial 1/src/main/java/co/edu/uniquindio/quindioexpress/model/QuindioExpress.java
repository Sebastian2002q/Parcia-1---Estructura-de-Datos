package co.edu.uniquindio.quindioexpress.model;

import co.edu.uniquindio.quindioexpress.comparator.PaqueteDespachoComparator;
import co.edu.uniquindio.quindioexpress.structures.ILista;
import co.edu.uniquindio.quindioexpress.structures.ListaSimpleEnlazada;
import com.sun.source.tree.Tree;

import java.util.*;

public class QuindioExpress {
    //Operativas
    private final ArrayList<Paquete> paquetes;
    private final HashMap<String, Paquete> paquetesPorCodigo;
    private final TreeSet<String> municipios;
    private final HashMap<String, List<Paquete>> paquetesPorDestino;
    private final PriorityQueue<Paquete> colaPrioridad;
    private final HashMap<String, Repartidor> repartidores;
    private final HashMap<String, String> asignaciones;

    //Historicas
    private final ArrayList<Paquete> historialPaquetes;
    private final HashMap<String, Paquete> historialPaquetesPorCodigo;
    private final TreeSet<String> historialMunicipios;
    private final HashMap<String, List<Paquete>> historialPaquetesPorDestino;
    private final ILista<Paquete> historialEntregas;

    public QuindioExpress() {
        paquetes = new ArrayList<>();
        historialPaquetes = new ArrayList<>();
        paquetesPorCodigo = new HashMap<>();
        historialPaquetesPorCodigo = new HashMap<>();
        municipios = new TreeSet<>();
        historialMunicipios = new TreeSet<>();
        paquetesPorDestino = new HashMap<>();
        historialPaquetesPorDestino = new HashMap<>();
        colaPrioridad = new PriorityQueue<>(new PaqueteDespachoComparator());
        repartidores = new HashMap<>();
        historialEntregas = new ListaSimpleEnlazada<>();
        asignaciones = new HashMap<>();
    }

    public boolean add(Paquete paquete) {
        if (paquete == null || historialPaquetesPorCodigo.containsKey(paquete.getCodigo())) {
            return false;
        }
        paquetes.add(paquete);
        historialPaquetes.add(paquete);
        paquetesPorCodigo.put(paquete.getCodigo(), paquete);
        historialPaquetesPorCodigo.put(paquete.getCodigo(), paquete);
        municipios.add(paquete.getDestino());
        historialMunicipios.add(paquete.getDestino());
        paquetesPorDestino.computeIfAbsent(paquete.getDestino(), destino -> new ArrayList<>()).add(paquete);
        historialPaquetesPorDestino.computeIfAbsent(paquete.getDestino(), destino -> new ArrayList<>()).add(paquete);
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

    public Repartidor getRepartidorByIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.isBlank()) {
            return null;
        }
        return repartidores.get(identificacion.trim());
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

    public boolean dispatchPaquete(TipoDespacho tipoDespacho) {
        if (tipoDespacho == null) {
            return false;
        }
        Paquete paquete;
        switch (tipoDespacho) {
            case FIFO:
                paquete = getSiguienteFIFO();
                break;
            case PRIORIDAD:
                paquete = getSiguientePrioridad();
                break;
            default:
                return false;
        }
        if (paquete == null) {
            return false;
        }
        if (!assignPaquete(paquete)) {
            return false;
        }
        if (tipoDespacho == TipoDespacho.PRIORIDAD) {
            colaPrioridad.poll();
        } else {
            colaPrioridad.remove(paquete);
        }
        return true;
    }

    public boolean confirmEntrega(String codigoPaquete) {
        if (codigoPaquete == null || codigoPaquete.isBlank()) {
            return false;
        }
        codigoPaquete = codigoPaquete.trim();
        String idRepartidor = asignaciones.get(codigoPaquete);
        if (idRepartidor == null) {
            return false;
        }
        Paquete paquete = paquetesPorCodigo.get(codigoPaquete);
        Repartidor repartidor = repartidores.get(idRepartidor);
        if (paquete == null || repartidor == null) {
            return false;
        }
        paquetes.remove(paquete);
        paquetesPorCodigo.remove(codigoPaquete);
        List<Paquete> paquetesDestino = paquetesPorDestino.get(paquete.getDestino());
        if (paquetesDestino != null) {
            paquetesDestino.remove(paquete);
            if (paquetesDestino.isEmpty()) {
                paquetesPorDestino.remove(paquete.getDestino());
                municipios.remove(paquete.getDestino());
            }
        }
        historialEntregas.add(paquete);
        asignaciones.remove(codigoPaquete);
        repartidor.setDisponible(true);
        return true;
    }

    private Paquete getSiguienteFIFO() {
        for (Paquete paquete : paquetes) {
            if (!asignaciones.containsKey(paquete.getCodigo())) {
                return paquete;
            }
        }
        return null;
    }

    private Paquete getSiguientePrioridad() {
        return colaPrioridad.peek();
    }

    private boolean assignPaquete(Paquete paquete) {
        if (paquete == null || !paquetes.contains(paquete)) {
            return false;
        }
        if (asignaciones.containsKey(paquete.getCodigo())) {
            return false;
        }
        for (Repartidor repartidor : repartidores.values()) {
            if (repartidor.isDisponible() && repartidor.getZona().equalsIgnoreCase(paquete.getDestino())) {
                asignaciones.put(paquete.getCodigo(), repartidor.getIdentificacion());
                repartidor.setDisponible(false);
                return true;
            }
        }
        return false;
    }

    public double getPesoTotalByMunicipio(String municipio) {
        if (municipio == null || municipio.isBlank()) {
            return 0;
        }
        List<Paquete> listPaquetes = paquetesPorDestino.get(municipio.trim());
        if (listPaquetes == null) {
            return 0;
        }
        return sumPesoMunicipio(listPaquetes, 0, listPaquetes.size() - 1);
    }

    private double sumPesoMunicipio(List<Paquete> listPaquetes, int inicio, int fin) {
        if (inicio == fin) {
            return listPaquetes.get(inicio).getPeso();
        }
        int mitad = (inicio + fin) / 2;
        double sumaIzq = sumPesoMunicipio(listPaquetes, inicio, mitad);
        double sumaDer = sumPesoMunicipio(listPaquetes, mitad + 1, fin);
        return sumaIzq + sumaDer;
    }

    public int countPaquetesByPrioridad(int prioridadMinima) {
        if (prioridadMinima < 1 || prioridadMinima > 5) {
            return 0;
        }
        if (paquetes.isEmpty()) {
            return 0;
        }
        return countPrioridad(paquetes, prioridadMinima, 0, paquetes.size() - 1);
    }

    private int countPrioridad(List<Paquete> lista, int prioridadMinima, int inicio, int fin) {
        if (inicio == fin) {
            return lista.get(inicio).getPrioridad() >= prioridadMinima ? 1 : 0;
        }
        int mitad = (inicio + fin) / 2;
        int cantidadIzq = countPrioridad(lista, prioridadMinima, inicio, mitad);
        int cantidadDer = countPrioridad(lista, prioridadMinima, mitad + 1, fin);
        return cantidadIzq + cantidadDer;
    }

    public Paquete binarySearchByCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return null;
        }
        List<Paquete> listaOrdenada = new ArrayList<>(paquetes);
        Collections.sort(listaOrdenada);
        return binarySearch(listaOrdenada, codigo.trim(), 0, listaOrdenada.size() - 1);
    }

    private Paquete binarySearch(List<Paquete> lista, String codigo, int inicio, int fin) {
        if (inicio > fin) {
            return null;
        }
        int mitad = (inicio + fin) / 2;
        Paquete paquete = lista.get(mitad);
        int comparacion = paquete.getCodigo().compareTo(codigo);
        if (comparacion == 0) {
            return paquete;
        }
        if (comparacion > 0) {
            return binarySearch(lista, codigo, inicio, mitad - 1);
        }
        return binarySearch(lista, codigo, mitad + 1, fin);
    }

    public Paquete getPaqueteMayorPeso() {
        if (paquetes.isEmpty()) {
            return null;
        }
        return findPaqueteMayorPeso(paquetes, 0, paquetes.size() - 1);
    }

    private Paquete findPaqueteMayorPeso(List<Paquete> lista, int inicio, int fin) {
        if (inicio == fin) {
            return lista.get(inicio);
        }
        int mitad = (inicio + fin) / 2;
        Paquete mayorIzq = findPaqueteMayorPeso(lista, inicio, mitad);
        Paquete mayorDer = findPaqueteMayorPeso(lista, mitad + 1, fin);
        if (mayorIzq.getPeso() >= mayorDer.getPeso()) {
            return mayorIzq;
        }
        return mayorDer;
    }

    public List<Paquete> getPaquetesOrdenados(Comparator<Paquete> comparator) {
        List<Paquete> listaOrdenada = new ArrayList<>(paquetes);
        listaOrdenada.sort(comparator);
        return listaOrdenada;
    }
}