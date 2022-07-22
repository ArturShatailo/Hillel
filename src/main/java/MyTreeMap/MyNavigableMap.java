package MyTreeMap;

import java.util.Set;
import java.util.SortedMap;

public interface MyNavigableMap<Integer,V> {

    Integer firstKey();

    Integer lastKey();

    MyTreeMap.Item<Integer, V> lastItem();

    MyTreeMap.Item<Integer, V> firstItem();

    Integer lowerKey(Integer key);
    Set<MyTreeMap.Item<Integer, V>> itemSet();

    MyTreeMap.Item<Integer, V> lowerItem(Integer key);

    Integer floorKey(Integer key);

    MyTreeMap.Item<Integer, V> floorItem(Integer key);

    MyTreeMap.Item<Integer, V> higherItem(Integer key);

    Integer higherKey(Integer key);

    Integer ceilingKey(Integer key);

    MyTreeMap.Item<Integer, V> ceilingItem(Integer key);

    long getLeftBlackHeight();

    long getRightBlackHeight();


}
