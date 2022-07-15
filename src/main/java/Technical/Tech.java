package Technical;

import java.util.Scanner;

public class Tech {

    //"GetInputFunction" is a scanner of input that returns Integer entered by user
    public static int GetInputFunction(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    //"GetInputStringFunction" is a scanner of input that returns String entered by user
    public static String GetInputStringFunction(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    //Get random value from min to max
    public static int getRandom(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static Double getRandomDouble(double min, double max) {
        return roundMet((Math.random() * (max - min)) + min);
    }

    public static double roundMet(double a){
        double b = Math.pow(10, 2);
        return Math.ceil(a * b) / b;
    }

}
