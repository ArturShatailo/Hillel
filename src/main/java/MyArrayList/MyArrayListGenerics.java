package MyArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class MyArrayListGenerics<T> implements MyListGenerics<T> {

    private T [] array = (T[]) new Object[0];

    public boolean addAll(Collection<? extends T> c) {
        T [] a = (T[]) c.toArray();
        T [] arrayNew = (T[]) new Object[array.length + a.length];
        System.arraycopy(array, 0, arrayNew, 0, array.length);
        System.arraycopy(a, 0, array, array.length, a.length);

        array = arrayNew;
        return true;
    }

    public void setArray(T[] a) {
        this.array = a;
    }

    @Override
    public String toString(){
        return Arrays.toString(Arrays.stream(array).toArray());
    }

    @Override
    public void add(T E) {
        T [] a = (T[]) new Object[array.length+1];
        System.arraycopy(array, 0, a, 0, a.length-1);
        a[a.length-1] = E;
        setArray(a);
    }

    @Override
    public boolean add(int i, T E) {
        try {
            T [] a = (T[]) new Object[array.length+1];
            System.arraycopy(array, 0, a, 0, i);
            a[i] = E;
            System.arraycopy(array, i, a, i + 1, array.length - i);
            setArray(a);
            return true;
        }catch (Exception e){
            setArray(array);
            return false;
        }
    }

    @Override
    public boolean removeByValue(T E) {
        try {
            int k = 0;
            for(T i : array){
                if(i.equals(E)){
                    remove(k);
                    k--;
                }
                k++;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean set(int i, T E) {
        try {
            array[i] = E;
            return true;
        }catch (Exception e){
            System.out.println("There is no index "+i+" in List");
            return false;
        }
    }

    @Override
    public void clear() {
        setArray((T[]) new Object[0]);
    }

    @Override
    public T get(int i) {
        try {
            return array[i];
        }catch (Exception e){
            System.out.println("There is no index "+i+" in List");
            return null;
        }
    }

    @Override
    public int indexOf(T o) {
        return indexOfRange(o, 0, array.length);
    }

    @Override
    public int indexOfRange(T o, int start, int end) {
        Object[] es = array;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean remove(int i) {
        try {
            T [] a = (T[]) new Object[array.length - 1];
            System.arraycopy(array, 0, a, 0, i);
            System.arraycopy(array, i + 1, a, i, array.length - ( i + 1 ));
            setArray(a);
            return true;
        }catch (Exception e){
            System.out.println("There is no index "+i+" in List");
            return false;
        }
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public List<T> subList(int f, int t) {
        T [] a = (T[]) new Object[t-f];
        System.arraycopy(array, f, a, 0, t-f);
        return Arrays.asList(a);
    }

    @Override
    public T[] toArray() {
        return array;
    }
}
