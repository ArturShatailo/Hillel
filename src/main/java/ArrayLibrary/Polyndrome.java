package ArrayLibrary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Polyndrome {

    static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.println("Please write a sentence and I will check if it's a polyndrome: ");
        String sentence = READER.readLine();
        System.out.println(Massive.isPolyndrome(sentence));

    }

}
