package MyArrayList;

import java.util.List;

public interface MyListGenerics<T> {

    void add(T E);

    boolean add(int i, T E);

    boolean removeByValue(T E);

    boolean set(int i, T E);

    void clear();

    T get(int i);

    boolean isEmpty();

    boolean remove(int i);

    int size();

    List<T> subList(int f, int t);

    T[] toArray();

}
