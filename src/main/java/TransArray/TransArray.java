package TransArray;

import java.util.Arrays;
import java.util.List;

public class TransArray {

    public static void main(String[] args) {

        //User should set 2 sizes of array
        System.out.println("Enter vertical size of array: ");
        int vertical = Tech.GetInputFunction();

        System.out.println("Enter horizontal size of array: ");
        int horizontal = Tech.GetInputFunction();

        //Array creating
        int[][] array = createAndFill(vertical, horizontal);
        //Array transposing
        int[][] arrayT = transpose(array);

        //Print results
        printIt(array);
        System.out.println("\n");
        printIt(arrayT);

    }

    //This method gets a 2d array as a parameter and prints its elements
    public static void printIt(int[][] array){
        for (int[] ints : array) {
            for (int intsIn : ints) {
                System.out.print(" "+intsIn+" ");
            }
            System.out.print("\n");
        }
    }

    //This method gets 2 sizes of 2d array as parameters and creates new array, by filling it with data from console.
    // Returns created 2d array
    public static int[][] createAndFill(int y, int x){
        int [][] array = new int[y][x];

        for(int i=0; i<y; i++){
            for(int k=0; k<x; k++){
                System.out.println("Enter item #"+(k+1)+ " of the row #"+(i+1)+" of your array");
                array[i][k] = Tech.GetInputFunction();
            }
        }
        return array;
    }

    //This method gets a 2d array, creates new array with inverted size (vertical to horizontal and horizontal to vertical).
    //Then, method stats stream of received array and taking each element of each row set it for new array as a column element.
    public static int[][] transpose(int[][] array){

        int[][] arrayT = new int[array[0].length][array.length];

        Arrays.stream(array)
                .forEach(n ->
                        Arrays.stream(n)
                        .forEach(m -> arrayT[Arrays.binarySearch(n, m)][List.of(array).indexOf(n)]=m));

        return arrayT;
    }



}
