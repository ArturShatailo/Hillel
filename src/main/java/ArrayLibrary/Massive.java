package ArrayLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class Massive{

    private static final ArrayList<int[]> intArrays = new ArrayList<>();
    private static final ArrayList<String[]> stringArrays = new ArrayList<>();

    public Massive() {

    }

    //The method receives an array and divides it for requested parts (second parameter).
    //returns ArrayList of arrays
    public static ArrayList divide(int[] array, int parts) {

        if(parts<=0){
            intArrays.add(array);
        }else {

            //variables. length of requested array, size of each part, amount of elements that will remain after dividing
            // in case of shared division
            int length = array.length;
            int loop = length / parts;
            int loopLast = length % parts;

            if (loopLast != 0) {
                if (length < parts) {
                    intArrays.add(array);
                } else {

                    for (int i = 0; i < parts; i++) {
                        int[] arr;
                        if(i==parts-1){
                            arr = new int[loop+loopLast];
                            for (int k = 0; k < loop; k++) {
                                arr[k] = array[(i * (loop)) + k];
                            }
                            for (int b = 0; b < loopLast; b++) {
                                arr[loop+b] = array[(length - loopLast) + b];
                            }
                        }else{
                            arr = new int[loop];
                            for (int k = 0; k < loop; k++) {
                                arr[k] = array[(i * (loop)) + k];
                            }
                        }
                        intArrays.add(arr);
                    }
                }
            } else {
                for (int i = 0; i < parts; i++) {

                    int[] arr = new int[loop];

                    for (int k = 0; k < loop; k++) {
                        arr[k] = array[(i * (loop)) + k];
                    }
                    intArrays.add(arr);
                }
            }
        }
        return intArrays;
    }
    public static ArrayList divide(String[] array, int parts) {

        if(parts<=0){
            stringArrays.add(array);
        }else {

            int length = array.length;
            int loop = length / parts;
            int loopLast = length % parts;

            if (loopLast != 0) {
                if (length < parts) {
                    stringArrays.add(array);
                } else {

                    for (int i = 0; i < parts; i++) {
                        String[] arr;
                        if(i==parts-1){
                            arr = new String[loop+loopLast];
                            for (int k = 0; k < loop; k++) {
                                arr[k] = array[(i * (loop)) + k];
                            }
                            for (int b = 0; b < loopLast; b++) {
                                arr[loop+b] = array[(length - loopLast) + b];
                            }
                        }else{
                            arr = new String[loop];
                            for (int k = 0; k < loop; k++) {
                                arr[k] = array[(i * (loop)) + k];
                            }
                        }
                        stringArrays.add(arr);
                    }
                }
            } else {
                for (int i = 0; i < parts; i++) {

                    String[] arr = new String[loop];

                    for (int k = 0; k < loop; k++) {
                        arr[k] = array[(i * (loop)) + k];
                    }
                    stringArrays.add(arr);
                }
            }
        }
        return stringArrays;

    }

    //The method receives an array as an argument and returns boolean if the array is sorted in grow direction
    public static boolean isGrowSorted(int[] array) {

        boolean d = false;

        if (array.length>1){
            for(int i=1; i<array.length; i++){
                if(array[i]>array[i-1]){
                    d=true;
                }else if(array[i] == array[i-1]){
                    d=true;
                }else{
                    return false;
                }
            }
        }else if(array.length==1){
            d = true;
        }

        return d;
    }
    public static boolean isGrowSorted(String[] array) {

        boolean d = false;

        if (array.length>1){
            for(int i=1; i<array.length; i++){
                if(array[i].length()>array[i-1].length()){
                    d=true;
                }else if(array[i].length() == array[i-1].length()){
                    d=true;
                }else{
                    return false;
                }
            }
        }else if(array.length==1){
            d = true;
        }

        return d;
    }

    //The method receives an array as an argument and returns boolean if the array is sorted in decline direction
    public static boolean isDeclineSorted(int[] array) {
        boolean d = false;

        if (array.length>1){
            for(int i=1; i<array.length; i++){
                if(array[i]<array[i-1]){
                    d=true;
                }else if(array[i] == array[i-1]){
                    d=true;
                }else{
                    return false;
                }
            }
        }else if(array.length==1){
            d = true;
        }

        return d;
    }
    public static boolean isDeclineSorted(String[] array) {

        boolean d = false;

        if (array.length>1){
            for(int i=1; i<array.length; i++){
                if(array[i].length()<array[i-1].length()){
                    d=true;
                }else if(array[i].length() == array[i-1].length()){
                    d=true;
                }else{
                    return false;
                }
            }
        }else if(array.length==1){
            d = true;
        }

        return d;
    }

    //The method receives an array as an argument and returns sorted in grow direction array
    public static int[] sortGrow(int[] array) {
        if (array.length>1) {
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    for (int k = i; k > 0; k--) {
                        if (array[k] < array[k - 1]) {
                            int d = array[k];
                            array[k] = array[k - 1];
                            array[k - 1] = d;
                        }
                    }
                }
            }
        }
        return array;
    }
    public static String[] sortGrow(String[] array) {
        if (array.length>1) {
            for (int i = 1; i < array.length; i++) {
                if (array[i].length() < array[i - 1].length()) {
                    for (int k = i; k > 0; k--) {
                        if (array[k].length() < array[k - 1].length()) {
                            String d = array[k];
                            array[k] = array[k - 1];
                            array[k - 1] = d;
                        }
                    }
                }
            }
        }
        return array;
    }

    //The method receives an array as an argument and returns sorted in decline direction array
    public static String[] sortDecline(String[] array) {
        if (array.length>1) {
            for (int i = 1; i < array.length; i++) {
                if (array[i].length() > array[i - 1].length()) {
                    for (int k = i; k > 0; k--) {
                        if (array[k].length() > array[k - 1].length()) {
                            String d = array[k];
                            array[k] = array[k - 1];
                            array[k - 1] = d;
                        }
                    }
                }
            }
        }
        return array;
    }
    public static int[] sortDecline(int[] array) {
        if (array.length>1) {
            for (int i = 1; i < array.length; i++) {
                if (array[i] > array[i - 1]) {
                    for (int k = i; k > 0; k--) {
                        if (array[k] > array[k - 1]) {
                            int d = array[k];
                            array[k] = array[k - 1];
                            array[k - 1] = d;
                        }
                    }
                }
            }
        }
        return array;
    }

    //The method receives an array, value and place (index) as arguments and returns a new array
    // equals to received but with inserted value on the specified place
    public static int[] insertInto(int value, int place, int[] array) {

        int[] arr = new int[array.length+1];

        if(array.length-1<place){
            place = array.length;
        }else if(place<0){
            return array;
        }

        for(int i=0, k=0; i<array.length || k<arr.length; i++, k++){

            if(i==place){
                arr[k] = value;
                k++;
            }
            arr[k] = array[i];
        }

        return arr;
    }
    public static String[] insertInto(String value, int place, String[] array) {

        String[] arr = new String[array.length+1];

        if(array.length-1<place){
            place = array.length;
        }else if(place<0){
            return array;
        }

        for(int i=0, k=0; i<array.length || k<arr.length; i++, k++){

            if(i==place){
                arr[k] = value;
                k++;
            }
            arr[k] = array[i];
        }

        return arr;
    }

    //The method receives an array and place (index) as arguments and returns a new array
    // equals to received but with deleted value on the specified place
    public static int[] deleteFrom(int place, int[] array) {

        int[] arr = new int[array.length-1];

        if(array.length-1<place){
            return array;
        }else if(place<0){
            return array;
        }

        for(int i=0, k=0; i<array.length || k<arr.length; i++, k++){

            if(i==place){
                i++;
                arr[k] = array[i];
                continue;
            }
            arr[k] = array[i];
        }

        return arr;
    }
    public static String[] deleteFrom(int place, String[] array) {

        String[] arr = new String[array.length-1];

        if(array.length-1<place){
            return array;
        }else if(place<0){
            return array;
        }

        for(int i=0, k=0; i<array.length || k<arr.length; i++, k++){

            if(i==place){
                i++;
                arr[k] = array[i];
                continue;
            }
            arr[k] = array[i];
        }

        return arr;
    }

    public static boolean isPolyndrome(String sentence) {

        boolean b;

        char[] string = sentence.toCharArray();
        char[] stringC = new char[string.length];

        for (int i = string.length - 1, k=0; i >= 0 && k<stringC.length; i--, k++) {
            stringC[k] = string[i];
        }

        System.out.println(Arrays.toString(string));
        System.out.println(Arrays.toString(stringC));

        b = Arrays.toString(string).equalsIgnoreCase(Arrays.toString(stringC));
        return b;
    }


//    public static int[] doForEach(int[] array, Predicate<Integer> p) {
//
//        return array;
//    }

}
