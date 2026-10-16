package co.edu.uniquindio.quindioexpress.structures;

public interface Ilista<T> extends Iterable<T> {
    void add(T elemento);
    boolean remove(T elemento);
    T get(int indice);
    int size();
    boolean isEmpty();
}
