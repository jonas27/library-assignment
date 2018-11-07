package ex3;

import java.util.HashMap;
import java.util.Map;

public class Room {

    Map<String, Row> rows;

    Room() {
        rows = new HashMap();
    }

    public void addRow(String name) {
        rows.put(name, new Row());
    }

    public Row getRow(String name) {
        return rows.get(name);
    }

    public boolean rowExists(String name) {
        return rows.containsKey(name);
    }

    public void removeRow(String name) {
        rows.remove(name);
    }

    Book search(String isbn) {
        for (Map.Entry<String, Row> r : rows.entrySet()) {
            Book b = r.getValue().search(isbn);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    protected void addBook(Book book){
        rows.get(book.getTag().getRow()).addBook(book);
    }
}
