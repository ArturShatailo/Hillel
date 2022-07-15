package MyLinkedList;

public interface MyList<T> {

    boolean add(T o);

    void add(int i, T o);

    void set(int i, T o);

    void clear();

    T get(int i);

    boolean isEmpty();

    int size();

    void remove(T o);

}
