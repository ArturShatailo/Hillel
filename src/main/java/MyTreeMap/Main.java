package MyTreeMap;

public class Main {

    public static void main(String[] args) {
        MyTreeMap<Integer, String> a = new MyTreeMap<Integer, String>();

        a.insert(14, "a");
        a.insert(50, "n");
        a.insert(25, "b");
        a.insert(30, "c");
        a.insert(11, "y");
        a.insert(12, "k");
        a.insert(5, "o");
        a.insert(4, "r");
        a.insert(1, "l");
        a.insert(15, "m");
        a.insert(28, "q");
        a.insert(9, "n");
        a.insert(10, "n");

        a.printTree();

        System.out.println("firstKey() method demonstration: ");
        System.out.println(a.firstKey());
        System.out.println("lastKey() method demonstration: ");
        System.out.println(a.lastKey());
        System.out.println("_________________");

        System.out.println("getItemByKey(10) method demonstration: ");
        System.out.println(a.getItemByKey(10));
        System.out.println("_________________");

        System.out.println("lowerItem(10) method demonstration: ");
        System.out.println(a.lowerItem(10));
        System.out.println("_________________");

        System.out.println("lowerKey(10) method demonstration: ");
        System.out.println(a.lowerKey(10));
        System.out.println("_________________");

        System.out.println("higherKey(10) method demonstration: ");
        System.out.println(a.higherKey(10));
        System.out.println("_________________");

        System.out.println("update(10, 'g') method demonstration: ");
        a.update(10, "g");
        a.printTree();

        System.out.println("delete(25) method demonstration: ");
        a.delete(25);
        a.printTree();

        System.out.println("delete(5) method demonstration: ");
        a.delete(5);
        a.printTree();

    }
}
