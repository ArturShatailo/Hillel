package ArrayLibrary;

import java.util.Scanner;

public class Tech {

    public static Scanner scan = new Scanner(System.in);

    //"GetInputFunction" is a scanner of input that returns Integer entered by user
    public static int GetInputFunction(){
        return scan.nextInt();
    }

    //"GetInputStringFunction" is a scanner of input that returns String entered by user
    public static String GetInputStringFunction(){
        return scan.nextLine();
    }

    public static Integer getRandom(int min, int max){
        return (int) (Math.random()*(max-min))+min;
    }

}
