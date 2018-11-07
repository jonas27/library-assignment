package ex2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestLibrary {

    private Library library;

    @Before
    public void init() {
        Library.books = new ArrayList();
        library = new Library();
    }

    @Test
    public void testNumberOfBooks() {
        String fileLocation = "./src/main/resources/books.txt";
        List<Book> books = library.readFile(fileLocation);
        assertEquals(2, books.size());
    }

    @Test
    public void testNoBooks() {
        String fileLocation = "./src/main/resources/no-books.txt";
        List<Book> books = library.readFile(fileLocation);
        assertEquals(0, books.size());
    }

    @Test
    public void testManyBooks() {
        String fileLocation = "./src/main/resources/many-books.txt";
        List<Book> books = library.readFile(fileLocation);
        assertEquals(200, books.size());
    }

    @Test
    public void testIncorrectFields() {
        String fileLocation = "./src/main/resources/incorrect-books.txt";
        List<Book> books = library.readFile(fileLocation);
        Book book = books.get(0);
        assertEquals(1, books.size());
        assertEquals(0, books.get(0).getAuthors().size());
        assertEquals("Texts from Denmark", books.get(0).getTitle());
        assertEquals(2001, books.get(0).getPublicationYear());
    }


}
