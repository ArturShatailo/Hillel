package Library;

import java.util.Set;

public interface Librarian {

    void reviewDatabase();

    Set<Book> listBooksByAuthors(String a);

    boolean checkAuthor(String a);
}
