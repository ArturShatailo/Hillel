package MyLinkedList;

public interface MyDeque<T> {

    int size();

    boolean isEmpty();

    boolean contains(T o);

    boolean add(T o);

    void clear();

    boolean offer(T o);

    T remove(int i);

    T poll();

    T element();

    T peek();

    void addFirst(T o);

    void addLast(T o);

}
