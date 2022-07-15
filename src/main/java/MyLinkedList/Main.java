package MyLinkedList;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<Integer> i = new MyLinkedList<>();

        System.out.println("Is 'MyArrayList empty?: " + i.isEmpty());
        System.out.println("Fill out the MyArrayList with Integer. Input one by one after 'enter'. Double 'enter' will end the input");

        inputValues(i);

        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Input index to paste an element: ");
        int index = Tech.GetInputFunction();
        System.out.println("Input Integer element to paste on the index " + index + " place");
        int element = Tech.GetInputFunction();
        i.add(index, element);
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Input Integer element to paste on the last place");
        int last = Tech.GetInputFunction();
        i.addLast(last);
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Input Integer element to paste on the first place");
        int first = Tech.GetInputFunction();
        i.addFirst(first);
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Input index of element you would like to get: ");
        System.out.println("Your element is: " + i.get(Tech.GetInputFunction()));


        System.out.println("Input index of element you would like to remove: ");
        i.remove(Tech.GetInputFunction());
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Input Integer element you would like to remove: ");
        Integer j = Tech.GetInputFunction();
        i.remove(j);
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Input index to replace the element: ");
        int index1 = Tech.GetInputFunction();
        System.out.println("Input Integer element to place on the index " + index1 + " place");
        int element1 = Tech.GetInputFunction();
        i.set(index1, element1);
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Element method demonstration (with Exception). The first link is: ");
        System.out.println(i.element());


        System.out.println("Peek method demonstration (with null). The first link is: ");
        System.out.println(i.peek());


        System.out.println("Poll method demonstration (with null). The first link (removed) is: ");
        System.out.println(i.poll());
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Offer method demonstration. Input Integer element: ");
        i.offer(Tech.GetInputFunction());
        System.out.println("Your MyArrayList is: " + i);


        System.out.println("Your MyArrayList size is: " + i.size());

    }

    private static void inputValues(MyLinkedList<Integer> i) {

        boolean active = true;
        while (active) {
            String a = Tech.GetInputStringFunction();
            if (a.equals("")) {
                active = false;
            } else {
                i.add(Integer.parseInt(a));
            }
        }

    }
}