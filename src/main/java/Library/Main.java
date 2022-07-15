package Library;
import Technical.Tech;
public class Main {

    public static void main(String[] args) {

        //New Library object creation
        Library l = new Library();

        //Filling 'books' field Set collection of Library object with new Book objects
        l.reviewDatabase();

        //Getting user's choice
        System.out.println("Input author name please (Stephen King, Ernest Hemingway, George Orwell)");
        String author = Tech.GetInputStringFunction();

        //Validation of user's input and if it's correct, displaying Set collection of Book objects as a result
        if (l.checkAuthor(author))
            l.listBooksByAuthors(author).forEach(System.out::println);
        else
            System.out.println("Sorry this author is absent");
    }

}
