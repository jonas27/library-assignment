package ex2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSearch {

    private Library library;

    @Before
    public void init() {
        Library.books = new ArrayList();
        library = new Library();
    }

    @Test
    public void testString() {
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        String search = "from";
        List<Book> books = Search.findBooks(search);
        assertEquals(2, books.size());
    }

    @Test
    public void testSearchAllFields() {
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        String search = "Peter";
        List<Book> books = Search.findBooks(search);
        assertEquals(1, books.size());
        search = "Texts";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
        search = "Borgen";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
        search = "2012";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
    }

    @Test
    public void testMultiString() {
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        String search = "from abroad";
        List<Book> books = Search.findBooks(search);
        assertEquals(1, books.size());
    }

    @Test
    public void testInt() {
        String search = "2001";
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        List<Book> books = Search.findBooks(search);
        assertEquals(1, books.size());
    }

    @Test
    public void testSimpleWildcard() {
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        String search = "P*r";
        List<Book> books = Search.findBooks(search);
        assertEquals(1, books.size());
        assertEquals("Hans Andersen", books.get(0).getAuthors().get(1));
        search = "*gen";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
        search = "Bor*";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
    }

    @Test
    public void testComplexWildcard() {
        String search = "S*o*e*";
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        List<Book> books = Search.findBooks(search);
        assertEquals(1, books.size());
    }

    @Test
    public void testAnd() {
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        String search = "from &Peter";
        List<Book> books = Search.findBooks(search);
        assertEquals(1, books.size());
        search = "from& Peter";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
        search = "from&Peter";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
        search = "from & Peter";
        books = Search.findBooks(search);
        assertEquals(1, books.size());
    }

    @Test
    public void testAndWithWildcards() {
        String search = "S*o*e* & Brian";
        String fileLocation = "./src/main/resources/books.txt";
        library.readFile(fileLocation);
        List<Book> books = Search.findBooks(search);
        assertEquals(0, books.size());
    }


}
