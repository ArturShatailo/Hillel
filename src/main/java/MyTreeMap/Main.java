package MyTreeMap;

public class Main {

    public static void main(String[] args) {
        MyTreeMap<Integer, String> a = new MyTreeMap<Integer, String>();

        a.insert(3, "a");
        a.insert(5, "b");
        a.insert(4, "b");
        a.insert(1, "c");
        System.out.println(a.firstKey());
        System.out.println(a.lastKey());

        a.insert(10, "iii");
        a.insert(2, "oo");

        a.printTree();

        System.out.println("_________________");

        System.out.println(a.firstKey());
        System.out.println(a.lastKey());

        System.out.println("_________________");

        System.out.println(a.getItemByKey(4));
        System.out.println(a.getRightBlackHeight());
        System.out.println(a.getLeftBlackHeight());

        System.out.println("_________________");

        a.insert(7, "kk");
        a.insert(99, "mm");
        a.insert(12, "tt");
        a.insert(0, "po");

        a.insert(100, "f");
        a.insert(45, "yy");
        a.insert(309, "op");
        a.insert(15, "bb");
        a.insert(101, "f");
        a.insert(46, "yy");
        a.insert(308, "op");
        a.insert(16, "bb");
        a.insert(102, "f");
        a.insert(47, "yy");
        a.insert(309, "op");
        a.insert(17, "bb");

        System.out.println(a.lowerItem(3));
        System.out.println(a.lowerItem(13));

        a.printTree();
        System.out.println("_________________");

        System.out.println(a.lowerKey(3));
        System.out.println(a.lowerKey(10));

        System.out.println(a.higherKey(0));
        System.out.println(a.higherKey(4));

        System.out.println("_________________");

        System.out.println(a.getItemByKey(12));
        a.update(12, "truer");
        System.out.println(a.getItemByKey(12));

        a.printTree();

        System.out.println("_________________");


        a.delete(12);
        a.printTree();

        System.out.println("_________________");

        System.out.println(a.getRightBlackHeight());
        System.out.println(a.getLeftBlackHeight());

        a.delete(2);
        a.printTree();

        System.out.println(a.getRightBlackHeight());
        System.out.println(a.getLeftBlackHeight());
    }
}
