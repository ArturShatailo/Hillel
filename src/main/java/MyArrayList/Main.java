package MyArrayList;

import java.util.Arrays;

public class Main{

    public static void main(String[] args) {

        MyArrayListGenerics<Integer> i = new MyArrayListGenerics<>();

        System.out.println("Is 'MyArrayList empty?: "+i.isEmpty());
        System.out.println("Fill out the MyArrayList with Integer. Input one by one after 'enter'. Double 'enter' will end the input");

        inputValues(i);

        System.out.println("Your MyArrayList is: "+i);
        System.out.println("Input index to paste an element: ");
        int index = Tech.GetInputFunction();
        System.out.println("Input Integer element to paste on the index "+index+" place");
        int element = Tech.GetInputFunction();
        i.add(index, element);
        System.out.println("Your MyArrayList is: "+i);
        System.out.println("Input index of element you would like to get: ");
        System.out.println("Your element is: "+i.get(Tech.GetInputFunction()));
        System.out.println("Input index of element you would like to remove: ");
        i.remove(Tech.GetInputFunction());
        System.out.println("Your MyArrayList is: "+i);
        System.out.println("Input Integer element you would like to remove: ");
        System.out.println(i.removeByValue(Tech.GetInputFunction()));
        System.out.println("Your MyArrayList is: "+i);

        System.out.println("Input index to replace the element: ");
        int index1 = Tech.GetInputFunction();
        System.out.println("Input Integer element to place on the index "+index+" place");
        int element1 = Tech.GetInputFunction();
        i.set(index1, element1);
        System.out.println("Your MyArrayList is: "+i);

        System.out.println("Your MyArrayList size is: "+i.size());

        System.out.println("Your MyArrayList converted into Array is: "+Arrays.toString(i.toArray()));

        System.out.println("Input index of element from which to cut the MyArrayList: ");
        int indexCutFrom = Tech.GetInputFunction();
        System.out.println("Input index of element to which to cut the MyArrayList: ");
        int indexCutTo = Tech.GetInputFunction();
        i.subList(indexCutFrom, indexCutTo);
        System.out.println("Your subList MyArrayList is: "+i.subList(indexCutFrom, indexCutTo));

    }

    private static void inputValues(MyArrayListGenerics<Integer> i) {

        boolean active = true;
        while (active){
            String a = Tech.GetInputStringFunction();
            if (a.equals("")) {
                active = false;
            }else{
                i.add(Integer.parseInt(a));
            }
        }

    }

}
