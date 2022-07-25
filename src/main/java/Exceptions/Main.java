package Exceptions;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception, IOException, InterruptedException {


        //In method signature can be declared exceptions parents or the same exceptions as we throw
        if (System.currentTimeMillis() % 7 == 0) {
            throw new EOFException(); //but in signature declared IOException
        } else if(System.currentTimeMillis() % 5 == 1){
            throw new FileNotFoundException(); //but in signature declared IOException
        }

        f0();
        f1();
        f2();


        //The first catch is not available for non-matching exception type, so part of code after it is ignored
        //The execution is followed only after the requested exception catch type is found.

        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {throw new Exception();}
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); //RuntimeException doesn't match to Exception
            } finally {
                System.err.print(" 4"); // processed
            }
            System.err.print(" 5");     // not processed, exception is active, but catch block is not found yet
        } catch (Exception e) {
            System.err.print(" 6");     // processed, Exception catch matches Exception try
        } finally {
            System.err.print(" 7");     // processed
        }
        System.err.println(" 8");         // processed, catch block for Exception is already found.

        System.err.println("-----------------------");

        //There is catch for Error that works
        try {
            System.err.print(" 0");
            if (true) {throw new Error();}
            System.err.print(" 1");
        } catch(Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.println(" 4");


        System.err.println("-----------------------");

        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (Exception e) { //RuntimeException will be caught
            System.err.print(" 2");
        }
        System.err.println(" 3");

        System.err.println("-----------------------");

        System.err.println("#1.in");
        try {
            f(); //first method call
        } catch (Error e) { //catch exception form methods chain
            System.err.println("#1.CATCH");
        }
        System.err.println("#1.out");

        /*
        Finally will be executed even after return statement
         */
        try {
            return;
        } finally {
            System.err.println("finally");
        }

        //area(-3, -6);



    }

    public static void f0() throws EOFException {
        //this method declared EOFException but in the method of call can be declared its parent exception
    }
    public static void f1() throws FileNotFoundException {
        //this method declared EOFException but in the method of call can be declared its parent exception
    }
    public static void f2() throws InterruptedException {
        //The exception requested in this method is declared directly in the signature of main method
    }

    /*
    Method that doesn't return anything, but has double in signature.
     */
    public static void f() {
        System.err.println(".   #2.in");
        g(); //second method call
        System.err.println(".   #2.out");
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); //third method call
        System.err.println(".   .   #3.out");
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); //get out from the methods chain with throw
        }
        System.err.println(".   .   .   #4.out");
    }

    public static int area(int width, int height) throws Exception {
        Exception e = new Exception("Not allowed numbers");
        if (width < 0 || height < 0) {
            throw e;
        } else {
            return width * height;
        }
    }

}
