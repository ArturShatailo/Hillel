package Library;
import java.util.*;
import java.util.stream.Collectors;

public class Library implements Librarian{

    private final Set<Book> books = new HashSet<>();

    //CreateS and addS new Book objects into 'books' field Set collection
    @Override
    public void reviewDatabase() {
        books.add(new Book("It", "Stephen King", "1986"));
        books.add(new Book("Pet Sematary", "Stephen King", "1983"));
        books.add(new Book("Secret window, secret garden", "Stephen King", "1990"));
        books.add(new Book("The Old Man and the Sea", "Ernest Hemingway", "1952"));
        books.add(new Book("Snows of Kilimanjaro and Other Stories", "Ernest Hemingway", "1961"));
        books.add(new Book("The Sun Also Rises", "Ernest Hemingway", "1926"));
        books.add(new Book("1984", "George Orwell", "1949"));
        books.add(new Book("Animal Farm", "George Orwell", "1945"));
    }

    /*
    * Returns a Set collection from 'books' field Set collection filled with Book objects that have 'author'
    * field equals to @param String 'a'
    */
    @Override
    public Set<Book> listBooksByAuthors(String a) {
        return books.stream()
                .filter( b -> b.author().equals(a))
                .collect(Collectors.toSet());
    }

    /*
    * Checks if there are Book objects in 'books' Set collection field with 'author' field equals to @param String a
    * Returns false if absent;
    */
    @Override
    public boolean checkAuthor(String a) {
        return books.stream().anyMatch( b -> b.author().equals(a));
    }

}
