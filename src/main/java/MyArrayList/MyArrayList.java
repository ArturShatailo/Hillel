package MyArrayList;

import java.util.Arrays;
import java.util.List;

public class MyArrayList implements MyList {

    private Integer [] array = new Integer[0];

    public void setArray(Integer[] a) {
        this.array = a;
    }

    @Override
    public String toString(){
        return Arrays.toString(Arrays.stream(array).toArray());
    }


    //Add @param E into 'array' field array on the last position
    @Override
    public void add(Integer E) {
            Integer [] a = new Integer[array.length+1];
            System.arraycopy(array, 0, a, 0, a.length-1);
            a[a.length-1] = E;
            setArray(a);
    }

    //Add @param E into 'array' field array on the set position in the @param i
    @Override
    public boolean add(int i, Integer E) {
        try {
            Integer[] a = new Integer[array.length + 1];
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

    //replace 'array' with the same type array with length 0 (empty)
    @Override
    public void clear() {
        setArray(new Integer[0]);
    }

    //get Element of 'array' field with the set index in @param i
    @Override
    public Integer get(int i) {
        try {
            return array[i];
        }catch (Exception e){
            System.out.println("There is no index "+i+" in List");
            return null;
        }
    }

    //if 'array' length is 0, returns true, because it means that 'array' is empty
    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    //remove element with set index in @param i from 'array' field array
    @Override
    public boolean remove(int i) {
        try {
            Integer[] a = new Integer[array.length - 1];
            System.arraycopy(array, 0, a, 0, i);
            System.arraycopy(array, i + 1, a, i, array.length - ( i + 1 ));
            setArray(a);
            return true;
        }catch (Exception e){
            System.out.println("There is no index "+i+" in List");
            return false;
        }
    }

    //remove element set in @param V from 'array' field array
    @Override
    public boolean removeByValue(Integer V) {
        try {
            int k = 0;
            for(Integer i : array){
                if(i.equals(V)){
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

    //set element received in @param E as an element of 'array' field with index received from @param i
    @Override
    public boolean set(int i, Integer E) {
        try {
            array[i] = E;
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
    public Integer[] toArray() {
        return array;
    }

    //returns List with elements that is placed in 'array' between @param f and @param t indexes.
    @Override
    public List<Integer> subList(int f, int t) {
        Integer[] a = new Integer[t-f];
        System.arraycopy(array, f, a, 0, t-f);
        return Arrays.asList(a);
    }


}
