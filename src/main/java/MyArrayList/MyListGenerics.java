package MyArrayList;

import java.util.Collection;
import java.util.List;

public interface MyListGenerics<T> {

    void add(T E);

    int indexOf(T o);

    int indexOfRange(T o, int start, int end);

    boolean addAll(Collection<? extends T> c);

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
