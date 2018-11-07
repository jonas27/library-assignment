package ex3;

import java.util.HashMap;
import java.util.Map;

public class Library {

    private String name;
    private Map<String, Room> rooms;

    public Library(String name) {
        this.name = name;
        rooms = new HashMap();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRoom(String name) {
        rooms.put(name, new Room());
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public boolean roomExists(String name){
        return rooms.containsKey(name);
    }

    public void removeRoom(String name) {
        rooms.remove(name);
    }

    public Book search(String isbn) {
        for (Map.Entry<String, Room> r : rooms.entrySet()) {
            Book b = r.getValue().search(isbn);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public void addBook(Book book){
        rooms.get(book.getTag().getRoom()).addBook(book);
    }
}
