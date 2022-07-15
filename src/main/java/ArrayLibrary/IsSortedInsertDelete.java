package ArrayLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class IsSortedInsertDelete {

    static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.println("Please create 2 arrays. The first is Integer and the second is String. \n" +
                "First, enter length of these arrays: ");

        int lngth = Integer.parseInt(READER.readLine());

        int[] getArray = new int[lngth];
        String[] getArrayS = new String[lngth];

        //Filling out the arrays
        System.out.println("Now fill out your INT ARRAY: ");
        for (int i = 0; i < getArray.length; i++) {
            getArray[i] = Integer.parseInt(READER.readLine());
        }
        System.out.println("Fill out your STRING ARRAY: ");
        for (int i = 0; i < getArrayS.length; i++) {
            getArrayS[i] = READER.readLine();
        }


        System.out.println("________________________________");
        System.out.println("Now I will define if your arrays are grow sorted: ");

        //Call methods Massive.isGrowSorted
        boolean isGrowSorted = Massive.isGrowSorted(getArray);
        boolean isGrowSortedString = Massive.isGrowSorted(getArrayS);

        //Show interface (demonstration of the results)
        System.out.println("Is int array grow sorted?: " + isGrowSorted);
        System.out.println("Is String array grow sorted?: " + isGrowSortedString);


        System.out.println("________________________________");
        System.out.println("Now I will define if your arrays are decline sorted: ");

        //Call methods Massive.isDeclineSorted
        boolean isDeclineSorted = Massive.isDeclineSorted(getArray);
        boolean isDeclineSortedString = Massive.isDeclineSorted(getArrayS);

        //Show interface (demonstration of the results)
        System.out.println("Is int array decline sorted?: " + isDeclineSorted);
        System.out.println("Is String array decline sorted?: " + isDeclineSortedString);


        System.out.println("________________________________");
        System.out.println("Let's try to insert some value: 4 into the arrays, but you should choose the index: ");
        int place = Integer.parseInt(READER.readLine());

        //Call methods Massive.insertInto
        int[] inserted = Massive.insertInto(4, place, getArray);
        String[] insertedS = Massive.insertInto("4", place, getArrayS);

        //Show interface (demonstration of the results)
        System.out.println(Arrays.toString(inserted));
        System.out.println(Arrays.toString(insertedS));


        System.out.println("________________________________");
        System.out.println("Now let's delete from the arrays some value, but you should choose the index: ");
        int placeD = Integer.parseInt(READER.readLine());

        //Call methods Massive.deleteFrom
        int[] deleted = Massive.deleteFrom(placeD, getArray);
        //String[] deletedS = Massive.deleteFrom(placeD, getArrayS);

        //Show interface (demonstration of the results)
        System.out.println(Arrays.toString(deleted));
        //System.out.println(Arrays.toString(deletedS));

    }

}
