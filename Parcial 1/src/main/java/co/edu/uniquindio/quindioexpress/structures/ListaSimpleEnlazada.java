package co.edu.uniquindio.quindioexpress.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaSimpleEnlazada<T> implements ILista<T> {
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
    private int size;

    public ListaSimpleEnlazada() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void add(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (first == null) {
            first = nuevo;
            last = nuevo;
        } else {
            last.siguiente = nuevo;
            last = nuevo;
        }
        size++;
    }

    @Override
    public boolean remove(T elemento) {
        if (first == null) {
            return false;
        }
        if (Objects.equals(first.elemento, elemento)) {
            first = first.siguiente;
            size--;
            if (first == null) {
                last = null;
            }
            return true;
        }
        Nodo<T> actual = first;
        while (actual.siguiente != null) {
            if (Objects.equals(actual.siguiente.elemento, elemento)) {
                if (actual.siguiente == last) {
                    last = actual;
                }
                actual.siguiente = actual.siguiente.siguiente;
                size--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public T get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        Nodo<T> actual = first;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.elemento;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = first;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T elemento = actual.elemento;
                actual = actual.siguiente;
                return elemento;
            }
        };
    }
}