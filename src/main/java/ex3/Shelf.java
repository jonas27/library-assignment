package ex3;

import java.util.HashMap;
import java.util.Map;

public class Shelf {

    Map<String, Book> books;

    Shelf() {
        books = new HashMap();
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

//    Allow multiple copies. Not a nice solution with appending c though.
    protected void addBook(Book book){
        if(books.containsKey(book.getIsbn())){
            books.put(book.getIsbn() + "c", book);
            return;
        }
        books.put(book.getIsbn(), book);
    }
}
