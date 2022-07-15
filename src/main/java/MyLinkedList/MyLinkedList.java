package MyLinkedList;

import java.util.*;
public class MyLinkedList<T> implements MyDeque<T>, MyList<T> {

    /*
    amount of elements in chain (linked objects in MyLinkedList)
     */
    private int size;

    /*
    The first element link of the MyLinkedList
     */
    private MyNode<T> firstLink;

    /*
    The last element link of the MyLinkedList
     */
    private MyNode<T> lastLink;

    /*
    This field keep information about any changes in the current MyLinkedList object to prevent Exception to using object during
    any changes and addressing to changing element.
     */
    private transient int modificationsCounter = 0;

    /*
    Get the first link element. If there is no such element, throws a NoSuchElementException
     */
    public T getFirstLink() {
        MyNode<T> f = firstLink;
        try{
            return f.current;
        }catch (NoSuchElementException NO){
            System.out.println("Exception message: "+NO);
            return null;
        }
    }

    /*
    Get the last link element. If there is no such element, throws a NoSuchElementException
     */
    public T getLastLink() {
        MyNode<T> l = lastLink;
        try{
            return l.current;
        }catch (NoSuchElementException NO){
            System.out.println("Exception message: "+NO);
            return null;
        }
    }

    /*
    Get the element that is placed on @param 'i' place in MyLinkedList
     */
    @Override
    public T get(int i) {
        return getNode(i).current;
    }

    /*
    Add @param 'o' element into the chain of links MyLinkedList on the last place.
     */
    @Override
    public boolean add(T o) {
        linkLast(o);
        return true;
    }

    /*
    Add @param 'o' element into the chain of links MyLinkedList on the first place.
     */
    public void addFirst(T o) {
        linkFirst(o);
    }

    /*
    Add @param 'o' element into the chain of links MyLinkedList on the last place.
     */
    public void addLast(T o) {
        linkLast(o);
    }

    /*
    Add @param 'o' element into the chain of links MyLinkedList on the @param 'i' place. Returns IndexOutOfBoundsException Exception
    in case of MyLinkedList doesn't have such amount of elements or @param 'i' is negative.
     */
    @Override
    public void add(int i, T o) {

        checkIndexElement(i);

        if (i == size) {
            linkLast(o);
        } else if (i == 0) {
            linkFirst(o);
        } else {
            insertBefore(o, getNode(i));
        }

    }

    /*
    Element that is placed on @param 'i' place in MyLinkedList gets new value of 'current' field that is equal to @param 'o'
     */
    @Override
    public void set(int i, T o) {
        checkIndexElement(i);
        MyNode<T> r = getNode(i);
        r.current = o;
    }

    /*
    Removes an element from MyLinkedList that is placed on @param 'i' place
     */
    @Override
    public T remove(int i) {
        checkIndexElement(i);
        return excludeNode(getNode(i));
    }

    /*
    Removes all elements from MyLinkedList that is equal to @param 'o'
     */
    @Override
    public void remove(T o) {
        MyNode<T> c = firstLink;

        for (int k = 0; k < size; k++) {
            if (c.current.equals(o)) excludeNode(c);
            if (c.nextLink == null) break;
            c = c.nextLink;
        }
    }

    /*
    Returns 'size' field of MyLinkedList
     */
    @Override
    public int size() {
        return size;
    }

    /*
    Returns true if 'size' field of MyLinkedList is equal to 0
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    Returns true if MyLinkedList includes an element that is equal to @param 'o'
     */
    @Override
    public boolean contains(T o) {
        MyNode<T> c = firstLink;

        for (int k = 0; k < size; k++) {
            if (c.current.equals(o)) return true;
            if (c.nextLink == null) break;
            c = c.nextLink;
        }
        return false;
    }

    /*
    Sets null value for each field of elements in MyLinkedList
     */
    @Override
    public void clear() {

        MyNode<T> c = firstLink;

        for (int k = 0; k < size; k++) {
            if(c != null){
                MyNode<T> n = c.nextLink;
                if (n != null) c.nextLink = null;
                if (n != null) c.prevLink = null;
                c.current = null;
                c = n;
            }
        }

        firstLink = null;
        lastLink = null;
        size = 0;
        modificationsCounter++;
    }

    /*
    New element @param 'o' on the last place in MyLinkedList
     */
    @Override
    public boolean offer(T o) {
        return add(o);
    }

    /*
    Removes the first element and returns this removed element. If there is no such element, returns null
     */
    @Override
    public T poll() {
        final MyNode<T> f = firstLink;
        return (f == null) ? null : remove(0);
    }

    /*
    Returns the first element and throws IndexOutOfBoundsException in case of there is no such element
     */
    @Override
    public T element() {
        return getFirstLink();
    }

    /*
    Returns the first element. If there is no such element, returns null
     */
    @Override
    public T peek() {
        final MyNode<T> f = firstLink;
        return (f == null) ? null : f.current;
    }

    /*
    Sets null for each field of @param 'N' element in MyLinkedList and change the prevLink and nextLink fields for
    next element after @param 'N' and previous element before @param 'N' accordingly
     */
    private T excludeNode(MyNode<T> N) {

        final MyNode<T> p = N.prevLink;
        final MyNode<T> n = N.nextLink;

        if(p == null){
            if(n == null){
                N.current = null;
            }else{
                firstLink = n;
                firstLink.prevLink = null;
            }
        }else if(n == null){
            lastLink = p;
            lastLink.nextLink = null;
        }else{
            p.nextLink = n;
            n.prevLink = p;
        }

        size--;
        modificationsCounter++;

        return N.current;
    }

    /*
    Creates new element equals to @param 'o' in MyLinkedList before @param 'N' element.
    Changes the nextLink and prevLink fields for elements after previous element before @param 'N' element and next
    element after @param 'N' accordingly
     */
    private void insertBefore(T o, MyNode<T> N) {

        final MyNode<T> iNode = new MyNode<>(N.prevLink, o, N);
        N.prevLink = iNode;

        if (iNode.prevLink == null) {
            firstLink = iNode;
        } else {
            iNode.prevLink.nextLink = iNode;
        }

        size++;
        modificationsCounter++;

    }

    /*
    Sets firstLink field as a @param 'o'.
     */
    public void linkFirst(T o) {

        final MyNode<T> i = firstLink;
        final MyNode<T> iNode = new MyNode<>(null, o, i);

        firstLink = iNode;
        if (i == null) {
            lastLink = iNode;
        } else {
            i.prevLink = iNode;
        }
        size++;
        modificationsCounter++;
    }

    /*
    Sets lastLink field as a @param 'o'.
     */
    private void linkLast(T o) {

        final MyNode<T> i = lastLink;
        final MyNode<T> iNode = new MyNode<>(i, o, null);

        lastLink = iNode;
        if (i == null) {
            firstLink = iNode;
        } else {
            i.nextLink = iNode;
        }
        size++;
        modificationsCounter++;

    }

    /*
    Returns Node element that is placed on @param 'i' place in MyLinkedList
     */
    private MyNode<T> getNode(int i) {

        MyNode<T> c;
        if (i < (size / 2)) {
            c = firstLink;
            for (int k = 0; k < i; k++) {
                if (c.nextLink == null) break;
                c = c.nextLink;
            }
        } else {
            c = lastLink;
            for (int k = 0; k < size - i - 1; k++) {
                if (c.prevLink == null) break;
                c = c.prevLink;
            }
        }
        return c;

    }

    /*
    Check if @param 'i' is not negative and less than size field value. In case of false, throws an Exception
    */
    private void checkIndexElement(int i) {
        if (i >= size || i < 0)
            throw new IndexOutOfBoundsException("Index "+i+" is out of range");
    }


    @Override
    public String toString() {

        if(size == 0) return "[]";

        StringBuilder a = new StringBuilder();
        a.append("[");
        MyNode<T> m = firstLink;
        int k = 0;

        while (k < size) {
            k++;
            a.append(m.current.toString());
            if (m.nextLink != null) {
                m = m.nextLink;
                a.append(", ");
            } else {
                a.append("]");
                break;
            }
        }

        return a.toString();
    }


    /*
    MyNode class. It's objects represent an elements of MyLinkedList
     */
    private static class MyNode<T> {

        protected T current;

        protected MyNode<T> prevLink;

        protected MyNode<T> nextLink;

        public MyNode(MyNode<T> prevLink, T current, MyNode<T> nextLink) {
            this.current = current;
            this.prevLink = prevLink;
            this.nextLink = nextLink;
        }
    }
}