package MyTreeMap;

public interface MyMap<Integer, V> {

    V insert(Integer key, V value);

    MyTreeMap.Item<Integer, V> getItemByKey(Integer key);

    int size();

    MyTreeMap.Item<Integer, V> update(Integer key, V value);

}
