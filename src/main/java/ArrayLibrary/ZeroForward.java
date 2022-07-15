package ArrayLibrary;

import java.util.Arrays;

public class ZeroForward {

    public static void main(String[] args) {

        int[] zero = new int[10];
        int[] zero1 = new int[10];
        System.out.println("Input 10 digits to fill out the array");
        for (int i = 0; i < zero.length; i++) {
            zero[i] = Tech.GetInputFunction();
        }

        System.arraycopy(zero, 0, zero1, 0, 10);

        System.out.println("\nFind 0 and translate them to the very beginning using stream() methods: 'anyMatch', 'sorted', 'forEach'");
        if(Arrays.stream(zero).anyMatch(point -> point == 0)){
            System.out.print("Sorted array: ");
            Arrays.stream(zero).sorted().forEach(System.out::print);
        }else{
            System.out.println("I can't find any 0 in your array");
        }


        System.arraycopy(zero1, 0, zero, 0, 10);

        System.out.println("\n\nFind 0 and translate them to the very beginning using my Massive method 'sortGrow'");
        if(Arrays.stream(zero).anyMatch(point -> point == 0)){
            System.out.println("Sorted array: "+Arrays.toString(Massive.sortGrow(zero)));
        }else{
            System.out.println("I can't find any 0 in your array");
        }


        System.arraycopy(zero1, 0, zero, 0, 10);

        System.out.println("\n\nFind 0 and translate them to the very beginning using my some different idea :)");
        if(Arrays.stream(zero).anyMatch(point -> point == 0)){

            int[] zeroForward = new int[10];
            int[] zeroIndex = new int[0];
            for(int i = 0, k = 1; i<zero.length && k<zero.length; i++){

                if(zero[i] == 0){
                    zeroIndex = new int[k];
                    zeroIndex[k-1] = 0;
                    k++;
                    zero = removeElement(zero, i);
                    i--;
                }
            }

            System.arraycopy(zeroIndex, 0, zeroForward, 0, zeroIndex.length);
            System.arraycopy(zero, 0, zeroForward, zeroIndex.length, zero.length);

            System.out.println(Arrays.toString(zeroForward));

        }else{
            System.out.println("I can't find any 0 in your array");
        }

    }

    public static int[] removeElement( int [] arr, int index ){
        int[] newArray = new int[arr.length - 1];
        int remainingPart = arr.length - ( index + 1 );
        System.arraycopy(arr, 0, newArray, 0, index);
        System.arraycopy(arr, index + 1, newArray, index, remainingPart);
        return newArray;
    }

}
