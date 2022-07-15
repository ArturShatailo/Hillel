package Library;

public record Book(String name, String author, String date) {

    @Override
    public String toString() {
        return  "name: '" + name + '\'' +
                ", author: '" + author + '\'' +
                ", date: '" + date + '\'';
    }
}
