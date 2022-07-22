package MyTreeMap;
import java.util.*;

public class MyTreeMap<Integer,V> implements MyNavigableMap<Integer,V>, MyMap<Integer, V>, java.io.Serializable {

    private transient Item<Integer, V> root;

    private transient int size = 0;

    private static final boolean WHITE = false;

    private static final boolean BLACK = true;

    public MyTreeMap() {

    }

    public static boolean compareIt(Object f, Object s) {
        return Objects.equals(f, s);
    }

    public static final class Item<Integer, V> {

        private Integer key;

        private V value;

        private Item<Integer, V> left;

        private Item<Integer, V> right;

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

//        @Override
//        public String toString() {
//            return "Item{" +
//                    "key=" + key +
//                    ", value=" + value +
//                    ", left=" + left +
//                    ", right=" + right +
//                    ", color=" + color +
//                    '}';
//        }
    }

    //CRUD OPERATIONS
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

    @Override
    public Item<Integer, V> update(Integer key, V value){

        Item<Integer, V> current = getItemByKey(key);
        current.setValue(value);
        return current;

    }

    public Item<Integer, V> delete(Integer key){

        Item<Integer, V> current = getItemByKey(key);

        if(current == null) return null;

        if(current.parent == null) return deleteRoot();

        if(current.right == null || current.left == null) return deleteOneChildItem(current);

        Item <Integer, V> lower = getLowestFrom(current.right);

        Item<Integer, V> newI = new Item<>(lower.key, lower.value, current.parent);

        if(isLeft(current)){

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

            if(current.right.left != null){
                if(current.right.left.equals(lower) && current.right.left.right == null){
                    current.right.setRight(null);
                } else {
                    if(current.right.left.right != null) {
                        System.out.println(current.right.left.right);
                        current.right.left.right.setParent(current.right);
                        current.right.setLeft(current.right.left.right);
                    }
                }
            }

        } else {

            if(current.right.equals(lower) && lower.right == null){
                newI.setRight(null);
            } else {
                newI.setRight(current.right);
            }
            newI.setLeft(current.left);

            current.parent.setRight(newI);
            current.right.setParent(newI);
            current.left.setParent(newI);

            if(current.right.left != null){
                if(current.right.left.equals(lower) && current.right.left.right == null){
                    current.right.setLeft(null);
                } else {
                    if(current.right.left.right != null) {
                        System.out.println(current.right.left.right);
                        current.right.left.right.setParent(current.right);
                        current.right.setLeft(current.right.left.right);
                    }
                }
            }
        }
        deleteOneChildItem(lower);
        checkPositions(newI);
        return current;
    }

    private Item<Integer,V> deleteRoot() {

        Item <Integer, V> r = root;

        if(size == 1) {
            root.setValue(null);
            root.setKey(null);
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

        return r;
    }

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

    private Item<Integer,V> deleteOneChildItem(Item<Integer,V> i) {

        if(i.right == null && i.left == null ){
            if(isLeft(i))
                i.parent.setLeft(null);
            else
                i.parent.setRight(null);

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

        checkPositions(i.parent);

        return i;
    }

    private boolean isLeft(Item<Integer,V> i) {
        return i.parent.left.equals(i);
    }

    private void addNewItem(Integer key, V value, Item<Integer, V> parent, boolean isLeft) {

        Item<Integer, V> item = new Item<>(key, value, parent);

        if (isLeft) {
            parent.left = item;
        } else {
            parent.right = item;
        }

        checkPositions(item);
        size++;
    }

    private void checkPositions(Item<Integer, V> item) {

        item.color = WHITE;

        //UNCHECKED
        if(getRightBlackHeight() > getLeftBlackHeight()) {
            if(root.right != null && root.right.right != null) rotateLeft(root);
        } else {
            if(root.left != null && root.left.right != null) rotateRight(root);
        }

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

    private int compareKeys(Integer key, Integer key1) {

        if (key.equals(key1)) return 0;

        int k = java.lang.Integer.parseInt(String.valueOf(key));
        int k1 = java.lang.Integer.parseInt(String.valueOf(key1));

        return k < k1 ? -1 : 1;

    }

    private V addRootElement(Integer key, V value) {
        Item<Integer, V> rootElement = new Item<>(key, value, null);
        rootElement.setColor(BLACK);
        root = rootElement;
        size++;
        return value;
    }

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


    @Override
    public int size() {
        return size;
    }

    @Override
    public Integer firstKey() {
        return getFirstItem() == null ? null : getFirstItem().key;
    }

    @Override
    public Integer lastKey() {
        return getLastItem() == null ? null : getLastItem().key;
    }

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
     * If the requested key is equal to the current Item object of tree key, the loop returns the current Item object key.
     *
     * if the requested key is bigger than the current Item object of tree key, the loop will receive new element value
     * that is 'right' field of the current Item object in the loop. If 'left' field is null, returns current Item object.
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










    public void printTree() { // метод для вывода дерева в консоль
        Stack<Item<Integer, V>> globalStack = new Stack<>(); // общий стек для значений дерева
        globalStack.push(root);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack<Item<Integer, V>> localStack = new Stack<>(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                Item<Integer, V> temp = globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp); // выводим его значение в консоли
                    localStack.push(temp.left); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.right);
                    if (temp.left != null ||
                            temp.right != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }
}