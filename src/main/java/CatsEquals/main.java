package CatsEquals;
import Technical.Tech;

public class main {

    public static void main(String[] args) {

        boolean isTrue = createCat().areCatsEquals(createCat());
        System.out.println(isTrue);

    }


    public static Cat createCat(){
        Cat cat = new Cat();
        System.out.println("Create cat: ");

        System.out.println("Input name ");
        cat.setName(Tech.GetInputStringFunction());

        System.out.println("Input weight ");
        cat.setWeight(Tech.GetInputFunction());

        System.out.println("Input breed ");
        cat.setBreed(Tech.GetInputStringFunction());

        System.out.println("Input age ");
        cat.setAge(Tech.GetInputFunction());

        System.out.println(cat+"\n");

        return cat;
    }

}
