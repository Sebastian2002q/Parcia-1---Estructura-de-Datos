package co.edu.uniquindio.quindioexpress.structures;

public class ListaSimpleEnlazada<T> implements Ilista<T> {
    private static class Nodo<E> {
        private E elemento;
        private Nodo<E> siguiente;

        private Nodo(E elemento) {
            this.elemento = elemento;
            this.siguiente = null;
        }
    }

    private Nodo<T> first;
    private Nodo<T> last;
    private int tamanio;

    public ListaSimpleEnlazada() {
        first = null;
        last = null;
        tamanio = 0;
    }
    // Aquí implementaremos los métodos de ILista
}