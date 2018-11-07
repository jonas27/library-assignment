package ex3;

import java.util.HashMap;
import java.util.Map;

public class Row {

    Map<String, Shelf> shelfs;

    Row() {
        shelfs = new HashMap();
    }

    public void addShelf(String name) {
        shelfs.put(name, new Shelf());
    }

    public Shelf getShelf(String name) {
        return shelfs.get(name);
    }

    public boolean shelfExists(String name) {
        return shelfs.containsKey(name);
    }

    public void removeShelf(String name) {
        shelfs.remove(name);
    }

    Book search(String isbn) {
        for (Map.Entry<String, Shelf> r : shelfs.entrySet()) {
            Book b = r.getValue().getBook(isbn);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    protected void addBook(Book book){
        shelfs.get(book.getTag().getShelf()).addBook(book);
    }
}
