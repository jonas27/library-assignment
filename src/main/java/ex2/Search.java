package ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    private static final String ASTERISK = "(\\w+)";

    /**
     * findBooks searches the list of books in ex2.Library.
     * Every field in the book metadata is search.
     * The Asterisk only
     *
     * @param searchString
     * @return
     */
    public static List<Book> findBooks(String searchString) {
        List<Book> books = new ArrayList();
        Library.books.forEach(book -> {
            if (findInBook(book, searchString)) {
                books.add(book);
            }
        });
        return books;
    }

    private static boolean findInBook(Book b, String search) {
        search = search.replace("*", ASTERISK);
        String[] sArray = search.split("[ \\t]*&[ \\t]*");
        for (String s : sArray) {
            Pattern ptn = Pattern.compile(s);
            if (!(searchAuthors(b.getAuthors(), ptn) || searchField(b.getTitle(), ptn) || searchField(b.getPublisher(), ptn) || searchField(Integer.toString(b.getPublicationYear()), ptn))) {
                return false;
            }
        }
        return true;
    }

    private static boolean searchAuthors(List<String> authors, Pattern p) {
        for (String a : authors) {
            if (searchField(a, p)) {
                return true;
            }
        }
        return false;
    }

    private static boolean searchField(String field, Pattern p) {
        Matcher mt = p.matcher(field);
        return mt.find();
    }
}
