package CatsTransfer;
import Technical.Tech;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Input amount of cats: ");

        //Generates new List collection of Cat objects and displays it
        List<Cat> catsList = CatBuilder.fillCatsList(Tech.GetInputFunction());
        catsList.forEach(System.out::println);

        //Divider
        System.out.println("\n_______________________________________\n");

        //Changes 'address' field of each generated Cat object and displays the result
        List<Cat> catsListNewCity = CatBuilder.changeCatsAddress(catsList);
        catsListNewCity.forEach(System.out::println);

    }



}
