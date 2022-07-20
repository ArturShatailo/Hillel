package MyTreeMap;

import CatsEquals.Cat;

import java.util.*;

public class MyTreeMap<K,V>
        //extends AbstractMap<K,V>
        implements /*NavigableMap<K,V>,*/ Cloneable, java.io.Serializable
{

    private transient MyTreeMap.Item<K,V> root;

    private transient int size = 0;

    private static final boolean WHITE = false;

    private static final boolean BLACK = true;

    public boolean put(K key, V value){

        addItem(key, value);


    }

    private void addItem(K key, V value) {

        Item item = new Item(key, value, getParentItem(key, value));

    }

    private Item<K, V> getParentItem(K key, V value) {

        if(key < root.key){
            if
        }

    }


    public int size() {
        return size;
    }



    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return null;
    }

    @Override
    public K lowerKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        return null;
    }

    @Override
    public K floorKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return null;
    }

    @Override
    public K ceilingKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        return null;
    }

    @Override
    public K higherKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> lastEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super K> comparator() {
        return null;
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return null;
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return null;
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return null;
    }

    @Override
    public K firstKey() {
        return null;
    }

    @Override
    public K lastKey() {
        return null;
    }


   public static boolean compareIt(Object f, Object s){
        return Objects.equals(f, s);
    }

    public static final class Item<K,V>{

        private K key;

        private V value;

        private Item<K,V> left;

        private Item<K,V> right;

        private Item<K,V> parent;

        private boolean color = BLACK;

        public Item(K key, V value, Item<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean equals(Object o) {

            if(o == this) return true;
            if (o.getClass() != this.getClass()) return false;
            Item<K,V> item = (Item<K, V>) o;
            return compareIt(value, item.getValue()) && compareIt(key, item.getKey());
        }

        public int hashCode() {
            return (key==null ? 0 : key.hashCode()) ^ (value==null ? 0 : value.hashCode());
        }

        public String toString() {
            return key + " : " + value;
        }

    }



}
