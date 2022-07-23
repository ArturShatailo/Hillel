package MyTreeMap;
import java.util.*;

public class MyTreeMap<Integer,V> implements MyNavigableMap<Integer,V>, MyMap<Integer, V>{

    //root field contains the root element of tree (the starting)
    private transient Item<Integer, V> root;

    //amount of Item objects in the tree
    private transient int size = 0;

    //Color of Item set according to tree rules (there are no 2 white Items in a row and there is no white root element).
    //Leaves (end elements of each thread) with null value are always Black.
    private static final boolean WHITE = false;
    private static final boolean BLACK = true;

    public MyTreeMap() {}

    /**
     * Item is a part of key-value that has another additional fields for identification and tree build.
     *
     * @param <Integer> key of the pair 'key-value', in another word 'Entry'. In this implementation, renamed to 'Item'
     * @param <V> value of the pair 'key-value'
     */
    public static final class Item<Integer, V> {

        private Integer key;

        private V value;

        //left child
        private Item<Integer, V> left;

        //right child
        private Item<Integer, V> right;

        //parent Item of the current Item object in a tree
        private Item<Integer, V> parent;

        private boolean color = BLACK;

        public Item(Integer key, V value, Item<Integer, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public Integer getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean equals(Object o) {

            if (o == this) return true;
            if (o.getClass() != this.getClass()) return false;
            Item<Integer, V> item = (Item<Integer, V>) o;
            return compareIt(value, item.getValue()) && compareIt(key, item.getKey());
        }

        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }

        public String toString() {
            return key + " : " + value;
        }

        public void setLeft(Item<Integer, V> o) {
            this.left = o;
        }

        public void setRight(Item<Integer, V> o) {
            this.right = o;
        }

        public void setParent(Item<Integer, V> parent) {
            this.parent = parent;
        }

        public void setKey(Integer key) {
            this.key = key;
        }
    }

    /**
     * Checks if received Objects are equals.
     *
     * @param f the first Object to compare
     * @param s the second object to compare
     * @return boolean True if @param f is equal to @param s
     */
    public static boolean compareIt(Object f, Object s) {
        return Objects.equals(f, s);
    }

    //CRUD OPERATIONS

    /**
     * Use this method to get Item object that has field 'key' equals to received @param 'key'.
     *
     * The algorithm looks for the needed Item object by comparing 'key' field of iterated object and received in the
     * Integer parameter 'key'.
     *
     * @param key Integer variable that is a market for searching the specified Item object with equal 'key' field
     * @return Item object that matches to parameter 'key' according to the 'key' field.
     */
    @Override
    public Item<Integer, V> getItemByKey(Integer key) {
        int compare;
        Item<Integer, V> elem = root;
        Item<Integer, V> current = null;

        if(root.key.equals(key)) return root;

        while (elem != null) {
            compare = compareKeys(elem.key, key);

            if (compare < 0) {
                current = elem;
                elem = elem.right;
            } else if (compare > 0) {
                current = elem;
                elem = elem.left;
            } else {
                return elem;
            }
        }
        return current;
    }

    /**
     * Use this method to create Item object according to received parameters and insert this Item into the tree.
     *
     * Looks for the place to insert using compareKeys() method that compares @param 'key' and 'key' field of the
     * current iterated Item object.
     *
     * Item object creation is provided by the method addNewItem() or in case of adding root element the algorithm uses method
     * addRootElement().
     *
     * @param key the first parameter of the 'key-value' pair. Requested key of element that should be created and inserted
     * @param value the second parameter of the 'key-value' pair. Requested value of element that should be created and inserted.
     * @return 'value' field of the created Item object according to received parameters 'key' and 'value' and place of insertion.
     */
    @Override
    public V insert(Integer key, V value) {

        Item<Integer, V> i = root;

        if (i == null) return addRootElement(key, value);

        Item<Integer, V> elem = i;

        Item<Integer, V> current;

        int compare;

        do {
            compare = compareKeys(elem.key, key);
            if (compare > 0) {
                current = elem;
                elem = elem.left;
            } else if (compare < 0) {
                current = elem;
                elem = elem.right;
            } else {
                V prevValue = elem.value;
                elem.value = value;
                return prevValue;
            }

        } while (elem != null);

        addNewItem(key, value, current, compare > 0);

        return value;

    }

    /**
     * Updates 'value' field of the existing Item object with 'key' field that is equal to the received @param 'key'.
     * Gets needed Item objects using getItemByKey() method.
     *
     * @param key received requested 'key' to find specific Item object in the tree.
     * @param value the value to update 'value' field of searched Item object with.
     * @return updated Item with new 'value' field.
     */
    @Override
    public Item<Integer, V> update(Integer key, V value){

        Item<Integer, V> current = getItemByKey(key);
        current.setValue(value);
        return current;

    }

    /**
     * Exclude Item element from the tree and places specific thread into the empty place.
     *
     * In case of requested for excluding Item has no children, this Item object just will be excluded without replacing
     * The method deleteOneChildItem() is used.
     *
     * In case of excluding of 'root' Item, the method deleteRoot() is used. The algorithm deletes root Item replace it with
     * the smallest by 'key' element from 'right' side of the tree.
     *
     * In case of 2 children are connected to requested for excluding Item, the algorithm finds the smallest Item by 'key' from
     * right element of requested Item using method getLowestFrom() and moves it into the place of deleted Item.
     *
     * @param key requested 'key' to find Item object has to be deleted.
     * @return Item object that has been deleted or null in case of there is no such 'key' as in @param key requested.
     */
    @Override
    public Item<Integer, V> delete(Integer key){

        Item<Integer, V> current = getItemByKey(key);

        if(current == null) return null;

        if(current.parent == null) return deleteRoot();

        if(current.right == null || current.left == null) return deleteOneChildItem(current);

        Item <Integer, V> lower = getLowestFrom(current.right);

        Item<Integer, V> newI = new Item<>(lower.key, lower.value, current.parent);

        if(compareKeys(root.key, current.key) > 0/*isLeft(current)*/){

            if(current.right.equals(lower) && lower.right == null){
                newI.setRight(null);
            } else {
                newI.setRight(current.right);
            }
            newI.setLeft(current.left);

            if(isLeft(current)){
                current.parent.setLeft(newI);
            } else {
                current.parent.setRight(newI);
            }

            current.right.setParent(newI);
            current.left.setParent(newI);
/*
            if(current.right.left != null){
                if(current.right.left.equals(lower) && current.right.left.right == null){
                    current.right.setLeft(null);
                } else {
                    if(current.right.left.right != null) {
                        current.right.left.right.setParent(current.right);
                        current.right.setLeft(current.right.left.right);
                    }
                }
            }*/

        } else {
            System.out.println("kk");
            if(current.right.equals(lower) && lower.right == null){
                newI.setRight(null);
            } else {
                newI.setRight(current.right);
            }
            newI.setLeft(current.left);

            current.parent.setRight(newI);
            current.right.setParent(newI);
            current.left.setParent(newI);
/*
            if(current.right.left != null){
                if(current.right.left.equals(lower) && current.right.left.right == null){
                    //current.right.setLeft(null);
                } else {
                    if(current.right.left.right != null) {
                        current.right.left.right.setParent(current.right);
                        current.right.setLeft(current.right.left.right);
                    }
                }
            }*/
        }
        deleteOneChildItem(lower);
        return current;
    }

    /**
     * The algorithm deletes root Item replace it with the smallest by 'key' element from 'right' side of the tree.
     *
     * @return deleted root Item object
     */
    private Item<Integer,V> deleteRoot() {

        Item <Integer, V> r = root;

        if(size == 1) {
            root.setValue(null);
            root.setKey(null);
            size--;
            return r;
        }

        Item <Integer, V> lower;

        if(root.right != null){
            lower = getLowestFrom(root.right);
            root.right.setLeft(null);
        } else {
            lower = root.left;
        }

        lower.setParent(null);
        lower.setLeft(root.left);
        lower.setRight(root.right);
        root = lower;
        checkPositionsDelete(root);
        size--;
        return r;
    }

    /**
     * Finds the smallest by 'key' Item from the elements below the received in parameter 'item' Item.
     *
     * @param item requested Item object that is a marker of the beginning of search.
     * @return Item object that is the smallest one by 'key' in the space from @param 'item' to the end of thread
     */
    private Item<Integer,V> getLowestFrom(Item<Integer,V> item) {

        if(item.left == null) return item;

        Item<Integer, V> r = item;
        Item<Integer, V> current = item;

        while(r != null){
            current = r;
            r = r.left;
        }
        return current;
    }

    /**
     * Deletes one Item element and place its child Item thread into the place of itself. In case of there are no children elements,
     * just removes requested Item object from the tree.
     *
     * @param i requested Item that has been deleted
     * @return deleted Item.
     */
    private Item<Integer,V> deleteOneChildItem(Item<Integer,V> i) {

        if(i.right == null && i.left == null ){
            if(isLeft(i))
                i.parent.setLeft(null);
            else
                i.parent.setRight(null);

            checkPositionsDelete(i);
            size--;
            return i;
        }

        if(i.right == null){
            if(isLeft(i))
                i.parent.setLeft(i.left);
            else
                i.parent.setRight(i.left);
        } else {
            if(isLeft(i))
                i.parent.setLeft(i.right);
            else
                i.parent.setRight(i.right);
        }
        checkPositionsDelete(i);
        size--;
        return i;
    }

    /**
     * Checks if the received Item object is a 'left' element of it's parent Item object.
     * @param i requested Item object that should be checked.
     * @return True if @param 'i' Item object is placed in the 'left' field of parent Item object.
     */
    private boolean isLeft(Item<Integer,V> i) {
        if(i.parent.left == null) return false;
        return i.parent.left.equals(i);
    }

    /**
     * Creates new Item object and fills its fields out with values received in method parameters.
     * Calls method checkPositionsInsert to check colors and make rotates or colors changes if needed to save the tree rules.
     *
     * @param key 'key' value of 'key-value' pair of Item object
     * @param value 'value' value of 'key-value' pair of Item object
     * @param parent Item object that is a 'parent' one for the further created Item object.
     * @param isLeft boolean value that contains information about side of the future created Item object (left or right).
     */
    private void addNewItem(Integer key, V value, Item<Integer, V> parent, boolean isLeft) {

        Item<Integer, V> item = new Item<>(key, value, parent);

        if (isLeft) {
            parent.left = item;
        } else {
            parent.right = item;
        }

        checkPositionsInsert(item);
        size++;
    }


    /**
     * Checks colors of siblings and parent elements. The methods of balancing are copied from the initial TreeMap class
     *
     * The next methods colorOf(), parentOf(), setColor(), leftOf(), rightOf() are methods helpers that allows get requested data
     * in null safety way.
     *
     * @param item Item object that has been inserted
     */
    private void checkPositionsInsert(Item<Integer, V> item) {

        item.color = WHITE;

        while (item != null && item != root && item.parent.color == WHITE) {
            if (parentOf(item) == leftOf(parentOf(parentOf(item)))) {
                Item<Integer,V> secItem = rightOf(parentOf(parentOf(item)));
                if (colorOf(secItem) == WHITE) {
                    setColor(parentOf(item), BLACK);
                    setColor(secItem, BLACK);
                    setColor(parentOf(parentOf(item)), WHITE);
                    item = parentOf(parentOf(item));
                } else {
                    if (item == rightOf(parentOf(item))) {
                        item = parentOf(item);
                        rotateLeft(item);
                    }
                    setColor(parentOf(item), BLACK);
                    setColor(parentOf(parentOf(item)), WHITE);
                    rotateRight(parentOf(parentOf(item)));
                }
            } else {
                Item<Integer,V> secItem = leftOf(parentOf(parentOf(item)));
                if (colorOf(secItem) == WHITE) {
                    setColor(parentOf(item), BLACK);
                    setColor(secItem, BLACK);
                    setColor(parentOf(parentOf(item)), WHITE);
                    item = parentOf(parentOf(item));
                } else {
                    if (item == leftOf(parentOf(item))) {
                        item = parentOf(item);
                        rotateRight(item);
                    }
                    setColor(parentOf(item), BLACK);
                    setColor(parentOf(parentOf(item)), WHITE);
                    rotateLeft(parentOf(parentOf(item)));
                }
            }
        }
        root.color = BLACK;
    }

    private static <Integer,V> boolean colorOf(Item<Integer,V> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <Integer,V> Item<Integer,V> parentOf(Item<Integer,V> p) {
        return (p == null ? null: p.parent);
    }

    private static <Integer,V> void setColor(Item<Integer,V> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <Integer,V> Item<Integer,V> leftOf(Item<Integer,V> p) {
        return (p == null) ? null: p.left;
    }

    private static <Integer,V> Item<Integer,V> rightOf(Item<Integer,V> p) {
        return (p == null) ? null: p.right;
    }

    /**
     * Rotates tree on the left side around the received in parameter 'p' Item.
     * @param p Item object that should be used for rotation as the center.
     */
    private void rotateLeft(Item<Integer,V> p) {
        if (p != null) {
            Item<Integer,V> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    /**
     * Rotates tree on the right side around the received in parameter 'p' Item.
     * @param p Item object that should be used for rotation as the center.
     */
    private void rotateRight(Item<Integer,V> p) {
        if (p != null) {
            Item<Integer,V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }

    /**
     * Checks colors of siblings and parent elements. The methods of balancing are copied from the initial TreeMap class
     *
     * The next methods colorOf(), parentOf(), setColor(), leftOf(), rightOf() are methods helpers that allows get requested data
     * in null safety way.
     *
     * @param item Item object that has been deleted
     */
    private void checkPositionsDelete(Item<Integer,V> item) {
        while (item != root && colorOf(item) == BLACK) {
            if (item == leftOf(parentOf(item))) {
                Item<Integer,V> sib = rightOf(parentOf(item));

                if (colorOf(sib) == WHITE) {
                    setColor(sib, BLACK);
                    setColor(parentOf(item), WHITE);
                    rotateLeft(parentOf(item));
                    sib = rightOf(parentOf(item));
                }

                if (colorOf(leftOf(sib))  == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, WHITE);
                    item = parentOf(item);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, WHITE);
                        rotateRight(sib);
                        sib = rightOf(parentOf(item));
                    }
                    setColor(sib, colorOf(parentOf(item)));
                    setColor(parentOf(item), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(item));
                    item = root;
                }
            } else { // symmetric
                Item<Integer,V> sib = leftOf(parentOf(item));

                if (colorOf(sib) == WHITE) {
                    setColor(sib, BLACK);
                    setColor(parentOf(item), WHITE);
                    rotateRight(parentOf(item));
                    sib = leftOf(parentOf(item));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, WHITE);
                    item = parentOf(item);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, WHITE);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(item));
                    }
                    setColor(sib, colorOf(parentOf(item)));
                    setColor(parentOf(item), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(item));
                    item = root;
                }
            }
        }

        setColor(item, BLACK);
    }


    /**
     * Compares two received 'key' values converting them into String and then into int values to use '<' operator;
     *
     * @param key the first Integer elements to compare
     * @param key1 the second Integer element to compare
     * @return int value that is 1 if @param 'key' is smaller than 'key1', -1 in vise versa case and 0 in case of
     * 'key' and 'key1' are equals
     */
    private int compareKeys(Integer key, Integer key1) {

        if (key.equals(key1)) return 0;

        int k = java.lang.Integer.parseInt(String.valueOf(key));
        int k1 = java.lang.Integer.parseInt(String.valueOf(key1));

        return k < k1 ? -1 : 1;

    }

    /**
     * Creates new Item object and places it in the 'root' field.
     *
     * @param key value of 'key' field
     * @param value value of 'value' field
     * @return value of created Item object
     */
    private V addRootElement(Integer key, V value) {
        Item<Integer, V> rootElement = new Item<>(key, value, null);
        rootElement.setColor(BLACK);
        root = rootElement;
        checkPositionsInsert(rootElement);
        size++;
        return value;
    }

    /**
     * Finds the biggest by 'key' Item object.
     * @return found Item object with the biggest 'key' field value.
     */
    private Item<Integer, V> getLastItem() {

        if(size == 1) return root;
        if(size == 0) return null;

        Item<Integer, V> r = root;
        Item<Integer, V> c = null;

        while(r != null) {
            c = r;
            r = r.right;
        }
        return c;

    }

    /**
     * Finds the smallest by 'key' Item object.
     * @return found Item object with the smallest 'key' field value.
     */
    private Item<Integer, V> getFirstItem() {

        if(size == 1) return root;
        if(size == 0) return null;

        Item<Integer, V> r = root;
        Item<Integer, V> c = null;

        while(r != null) {
            c = r;
            r = r.left;
        }
        return c;

    }

    /**
     * Iterate all Item objects in TreeMap one by one from 'root' to last object on the left side and the last
     * object on the right side.
     * Uses Stack object to remember tree parts that was not checked to return later, after the main direction (left) check.
     *
     * @param i first Item object to start checking
     * @return Set of all Item objects into TreeMap
     **/
    private Set<Item<Integer, V>> iterateIntoSet(Item<Integer, V> i){

        if(size == 1) return new HashSet<>(Collections.singleton(root));

        Stack<Item<Integer, V>> stack = new Stack<> ();
        Set<Item<Integer, V>> set = new HashSet<>();

        while (i != null || !stack.empty()){

            if (!stack.empty()){
                i = stack.pop();
            }

            while (i != null) {
                set.add(i);
                if (i.right != null) stack.push(i.right);
                i = i.left;
            }
        }
        return set;
    }

    /**
     * @return 'size' field value
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return the 'key' field of Item received from getFirstItem() method
     */
    @Override
    public Integer firstKey() {
        return getFirstItem() == null ? null : getFirstItem().key;
    }

    /**
     * @return the 'key' field of Item received from getLastItem() method
     */
    @Override
    public Integer lastKey() {
        return getLastItem() == null ? null : getLastItem().key;
    }

    /**
     * @return Set of Item objects received from method iterateIntoSet().
     */
    @Override
    public Set<Item<Integer, V>> itemSet() {
        return iterateIntoSet(root);
    }

    /**
     * Checks size field and then calls method findLowerItem() to get Item that is the closer to and lower than
     * (according to keys values) value received in @param 'key'.
     *
     * @param key requested value to search according to
     * @return Item object found according to @param 'key' value and method findLowerItem().
     **/
    @Override
    public Item<Integer, V> lowerItem(Integer key) {
        return findLowerItem(key, false);
    }

    /**
     * Checks 'size' field and then calls method findLowerItem() to get Item that is the closer to and lower than
     * (according to keys values) value received in @param 'key'. Then gets 'key' field of found Item.
     *
     * @param key requested value to search according to
     * @return Integer key value of Item object found according to @param 'key' value and method findLowerItem().
     */
    @Override
    public Integer lowerKey(Integer key) {
        Item<Integer, V> i = findLowerItem(key, false);
        if(i == null) return null;

        return i.key;
    }

    @Override
    public Item<Integer, V> floorItem(Integer key) {
        if (key.equals(root.key)) return root;
        return findLowerItem(key, true);
    }

    @Override
    public Integer floorKey(Integer key) {
        if (key.equals(root.key)) return root.key;
        Item<Integer, V> i = findLowerItem(key, true);

        if(i == null) return null;

        return i.key;
    }

    @Override
    public Item<Integer, V> higherItem(Integer key) {
        return findHigherItem(key, false);
    }

    @Override
    public Integer higherKey(Integer key) {
        Item<Integer, V> i = findHigherItem(key, false);
        if(i == null) return null;

        return i.key;
    }

    @Override
    public Item<Integer, V> ceilingItem(Integer key) {
        return findHigherItem(key, true);
    }

    @Override
    public Integer ceilingKey(Integer key) {
        Item<Integer, V> i = findHigherItem(key, true);
        if(i == null) return null;

        return i.key;
    }

    @Override
    public Item<Integer, V> firstItem() {
        return getFirstItem();
    }

    @Override
    public Item<Integer, V> lastItem() {
        return getLastItem();
    }

    @Override
    public long getLeftBlackHeight(){
        if(size == 1) return 1;
        return iterateIntoSet(root.left).stream().filter( i -> i.color ).count();
    }

    @Override
    public long getRightBlackHeight(){
        if(size == 1) return 1;
        return iterateIntoSet(root.right).stream().filter( i -> i.color ).count();
    }

    /**
     * The while loop starts with comparing two 'key' (received key and current Item object key) values and writing
     * 1, -1 or 0 into compare int variable.
     *
     * If the requested key is equal to the current Item object of tree key, the loop returns the current Item object.
     *
     * if the requested key is bigger than the current Item object of tree key, the loop will receive new element value
     * that is 'right' field of the current Item object in the loop. If 'right' field is null, returns current Item object.
     *
     * If the requested key is smaller than the current Item object of tree key, the loop will receive new element value
     * that is 'left' field of the current Item object in the loop. If 'left' field is null, starts new loop to find
     * the first closer left Item object of parent Item.
     *
     * @param key requested value to search according to
     * @param include marker for clarification if equal key is included or excluded.
     * @return Item object found according to @param 'key' value
     */
    private Item<Integer, V> findLowerItem(Integer key, boolean include) {

        Item<Integer, V> i = root;

        while (i != null) {
            int compare = compareKeys(key, i.key);

            if(include && compare == 0) return i;

            if(compare > 0){
                if( i.right != null){
                    i = i.right;
                } else {
                    return i;
                }
            } else {
                if( i.left != null) {
                    i = i.left;
                } else {
                    Item<Integer,V> parent = i.parent;
                    Item<Integer,V> elem = i;

                    while (parent != null && elem == parent.left) {
                        elem = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
            }
        }
        return null;
    }

    /**
     * The while loop starts with comparing two 'key' (received key and current Item object key) values and writing
     * 1, -1 or 0 into compare int variable.
     *
     * If the requested key is equal to the current Item object of tree key, the loop returns the current Item object.
     *
     * if the requested key is smaller than the current Item object of tree key, the loop will receive new element value
     * that is 'left' field of the current Item object in the loop. If 'left' field is null, returns current Item object.
     *
     * If the requested key is bigger than the current Item object of tree key, the loop will receive new element value
     * that is 'right' field of the current Item object in the loop. If 'right' field is null, starts new loop to find
     * the first closer right Item object of parent Item.
     *
     * @param key requested value to search according to
     * @param include marker for clarification if equal key is included or excluded.
     * @return Item object found according to @param 'key' value
     */
    private Item<Integer,V> findHigherItem(Integer key, boolean include) {
        Item<Integer,V> i = root;

        while (i != null) {
            int compare = compareKeys(key, i.key);

            if(include && compare == 0) return i;

            if(compare < 0){
                if( i.left != null){
                    i = i.left;
                } else {
                    return i;
                }
            } else {
                if( i.right != null) {
                    i = i.right;
                } else {
                    Item<Integer,V> parent = i.parent;
                    Item<Integer,V> elem = i;

                    while (parent != null && elem == parent.right) {
                        elem = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
            }
        }
        return null;
    }

    /**
     * Prints visualization of tree in console
     */
    public void printTree() {

        //Tree Item objects Stack
        Stack<Item<Integer, V>> globalStack = new Stack<>();

        //insert 'root' element into Stack
        globalStack.push(root);

        //spaces between elements
        int gaps = 32;
        boolean isRowEmpty = false; //loop marker creation
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);

        while (!isRowEmpty) {

            //Stack of the parent objects of the current iterating element
            Stack<Item<Integer, V>> localStack = new Stack<>();
            isRowEmpty = true; //loop marker change

            for (int j = 0; j < gaps; j++) //print set amount of spaces
                System.out.print(' ');

            while (!globalStack.isEmpty()) { //checks globalStack for elements and starts new loop

                //get element from Stack and delete it.
                Item<Integer, V> temp = globalStack.pop();
                if (temp != null) {

                    System.out.print(temp); //print if not null

                    //insert children into localStack
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null || temp.right != null)
                        isRowEmpty = false; //change marker again

                } else {
                    System.out.print("____"); //if the element is empty, prints lines
                    localStack.push(null); //fill null objects as the element is empty
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2.1; //decrease the spaces amount before the new line start
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop()); //transfer all elements from localStack into globalStack
        }
        System.out.println(separator); //print line
    }
}



/*
    private void fixRedBlackPropertiesAfterInsert(Item<Integer, V> item) {
        Item<Integer, V> parent = item.parent;

        // Case 1: Parent is null, we've reached the root, the end of the recursion
        if (parent == null) {
            // Uncomment the following line if you want to enforce black roots (rule 2):
            // node.color = BLACK;
            return;
        }

        // Parent is black --> nothing to do
        if (parent.color == BLACK) {
            return;
        }

        // From here on, parent is red
        Item<Integer, V> grandparent = parent.parent;

        // Case 2:
        // Not having a grandparent means that parent is the root. If we enforce black roots
        // (rule 2), grandparent will never be null, and the following if-then block can be
        // removed.
        if (grandparent == null) {
            // As this method is only called on red nodes (either on newly inserted ones - or -
            // recursively on red grandparents), all we have to do is to recolor the root black.
            parent.color = BLACK;
            return;
        }

        // Get the uncle (may be null/nil, in which case its color is BLACK)
        Item<Integer, V> uncle = getUncle(parent);

        // Case 3: Uncle is red -> recolor parent, grandparent and uncle
        if (uncle != null && uncle.color == WHITE) {
            parent.color = BLACK;
            grandparent.color = WHITE;
            uncle.color = BLACK;

            // Call recursively for grandparent, which is now red.
            // It might be root or have a red parent, in which case we need to fix more...
            fixRedBlackPropertiesAfterInsert(grandparent);
        }

        // Parent is left child of grandparent
        else if (parent == grandparent.left) {
            // Case 4a: Uncle is black and node is left->right "inner child" of its grandparent
            if (item == parent.right) {
                rotateLeft(parent);

                // Let "parent" point to the new root node of the rotated sub-tree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = item;
            }

            // Case 5a: Uncle is black and node is left->left "outer child" of its grandparent
            rotateRight(grandparent);

            // Recolor original parent and grandparent
            parent.color = BLACK;
            grandparent.color = WHITE;
        }

        // Parent is right child of grandparent
        else {
            // Case 4b: Uncle is black and node is right->left "inner child" of its grandparent
            if (item == parent.left) {
                rotateRight(parent);

                // Let "parent" point to the new root node of the rotated sub-tree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = item;
            }

            // Case 5b: Uncle is black and node is right->right "outer child" of its grandparent
            rotateLeft(grandparent);

            // Recolor original parent and grandparent
            parent.color = BLACK;
            grandparent.color = WHITE;
        }
    }


    private Item<Integer, V> getUncle(Item<Integer, V> parent) {
        Item<Integer, V> grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }


    private void rotateRight(Item<Integer, V> item) {
        Item<Integer, V> parent = item.parent;
        Item<Integer, V> leftChild = item.left;

        item.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = item;
        }

        leftChild.right = item;
        item.parent = leftChild;

        replaceParentsChild(parent, item, leftChild);
    }


    private void rotateLeft(Item<Integer, V> item) {
        Item<Integer, V> parent = item.parent;
        Item<Integer, V> rightChild = item.right;

        item.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = item;
        }

        rightChild.left = item;
        item.parent = rightChild;

        replaceParentsChild(parent, item, rightChild);
    }


    private void replaceParentsChild(Item<Integer, V> parent, Item<Integer, V> oldChild, Item<Integer, V> newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }


*/