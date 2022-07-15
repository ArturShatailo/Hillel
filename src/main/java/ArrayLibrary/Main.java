package ArrayLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] getArray = new int[20];
        String[] getArrayS = new String[20];

        System.out.println("I created 2 arrays. The first is Integer and the second is String. \n" +
                "But I will not let you fill them out. The Random will do that faster!");

        //Filling out the arrays
        System.out.println("First INT ARRAY: ");
        for (int i = 0; i < getArray.length; i++) {
            getArray[i] = getRandom(0, 100);
            System.out.println(getArray[i]);
            //getArray[i] = Integer.parseInt(READER.readLine());
        }
        System.out.println("Second STRING ARRAY: ");
        for (int i = 0; i < getArrayS.length; i++) {
            int k = 0; StringBuilder b = new StringBuilder();
            while(getRandom(1, 10)>k) {
                b.append("a");
                k++;
            }
            getArrayS[i] = b.toString();
            System.out.println(getArrayS[i]);
            //getArrayS[i] = READER.readLine();
        }

        System.out.println("________________________________");
        System.out.println("Firstly, lets divide the arrays I created. Insert amount of parts to divide");
        int parts = Integer.parseInt(READER.readLine());

        //Call methods Massive.divide
        ArrayList divided = Massive.divide(getArray, parts);
        ArrayList dividedS = Massive.divide(getArrayS, parts);

        //Show interface (demonstration of the results)
        for (int i = 0; i < divided.size(); i++) {System.out.println((i+1)+")"+Arrays.toString((int[]) divided.get(i)));}
        for (int i = 0; i < dividedS.size(); i++) {System.out.println((i+1)+")"+Arrays.toString((String[]) dividedS.get(i)));}




        System.out.println("________________________________");
        System.out.println("Now I will insert into my arrays some value: 4, but you should choose the index: ");
        int place = Integer.parseInt(READER.readLine());

        //Call methods Massive.insertInto
        int[] inserted = Massive.insertInto(4, place, getArray);
        String[] insertedS = Massive.insertInto("4", place, getArrayS);

        //Show interface (demonstration of the results)
        System.out.println(Arrays.toString(inserted));
        System.out.println(Arrays.toString(insertedS));




        System.out.println("________________________________");
        System.out.println("Now I will delete from my arrays some value, but you should choose the index: ");
        int placeD = Integer.parseInt(READER.readLine());

        //Call methods Massive.deleteFrom
        int[] deleted = Massive.deleteFrom(placeD, getArray);
        String[] deletedS = Massive.deleteFrom(placeD, getArrayS);

        //Show interface (demonstration of the results)
        System.out.println(Arrays.toString(deleted));
        System.out.println(Arrays.toString(deletedS));




//        System.out.println("________________________________");
//        int[] forEachOne = Massive.doForEach(getArray, );
//        System.out.println(Arrays.toString(forEachOne));
//        String[] forEachOneS = Massive.doForEach(getArray, );
//        System.out.println(Arrays.toString(forEachOneS));
        System.out.println("________________________________");
        System.out.println("Now I will define if my arrays are grow sorted: ");

        //Call methods Massive.isGrowSorted
        boolean isGrowSorted = Massive.isGrowSorted(getArray);
        boolean isGrowSortedString = Massive.isGrowSorted(getArrayS);

        //Show interface (demonstration of the results)
        System.out.println("Is int array grow sorted?: " + isGrowSorted);
        System.out.println("Is String array grow sorted?: " + isGrowSortedString);





        System.out.println("________________________________");
        System.out.println("Now I will define if my arrays are decline sorted: ");

        //Call methods Massive.isDeclineSorted
        boolean isDeclineSorted = Massive.isDeclineSorted(getArray);
        boolean isDeclineSortedString = Massive.isDeclineSorted(getArrayS);

        //Show interface (demonstration of the results)
        System.out.println("Is int array decline sorted?: " + isDeclineSorted);
        System.out.println("Is String array decline sorted?: " + isDeclineSortedString);




        System.out.println("________________________________");
        System.out.println("So, let's sort them by grow: ");

        //Call methods Massive.sortGrow
        int[] sortedGrow = Massive.sortGrow(getArray);
        String[] sortedGrowS = Massive.sortGrow(getArrayS);

        //Show interface (demonstration of the results)
        System.out.println("Grow sorted int: " + Arrays.toString(sortedGrow));
        System.out.println("Grow sorted String: " + Arrays.toString(sortedGrowS));




        System.out.println("________________________________");
        System.out.println("So, let's sort them by decline: ");

        //Call methods Massive.sortDecline
        int[] sortedDecline = Massive.sortDecline(sortedGrow);
        String[] sortedDeclineS = Massive.sortDecline(getArrayS);

        //Show interface (demonstration of the results)
        System.out.println("Decline sorted int: " + Arrays.toString(sortedDecline));
        System.out.println("Decline sorted String: " + Arrays.toString(sortedDeclineS)+"\n\n");



        System.out.println("And finally, please write a sentence and I will check if it's a polyndrome: ");
        String sentence = READER.readLine();
        System.out.println(Massive.isPolyndrome(sentence));


    }

    public static int getRandom(int min, int max){

        return (int) (Math.random()*(max-min))+min;
    }

}
