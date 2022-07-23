package MyTreeMap;

public class Main {

    public static void main(String[] args) {
        MyTreeMap<Integer, String> a = new MyTreeMap<Integer, String>();

        a.insert(16, "a");
        a.insert(50, "n");
        a.insert(25, "b");
        a.insert(30, "c");
        a.insert(13, "y");
        a.insert(14, "k");
        a.insert(6, "o");
        a.insert(4, "r");
        a.insert(1, "l");
        a.insert(17, "m");
        a.insert(28, "q");
        a.insert(9, "t");
        a.insert(12, "w");
        a.insert(5, "j");
        a.insert(11, "c");

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

        System.out.println("delete(6) method demonstration: ");
        a.delete(6);
        a.printTree();

    }
}
