package MyArrayList;

import java.util.List;

public interface MyList {

    void add(Integer E);

    boolean add(int I, Integer E);

    boolean removeByValue(Integer V);

    boolean set(int i, Integer E);

    void clear();

    Integer get(int i);

    boolean isEmpty();

    boolean remove(int i);

    int size();

    List<Integer> subList(int fromIndex, int toIndex);

    Integer[] toArray();

}
