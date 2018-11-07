package ex2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {

    private static final Pattern ptn = Pattern.compile("(\\w+):[ \\t]*([\\w\\s]*)");
    //    books acts as a database.
    public static List<Book> books = new ArrayList<>();

    /**
     * readFile reads book meta data from a file and inserts it into a list.
     * It does not test for completness of data
     *
     * @param input
     * @return
     */
    public List<Book> readFile(String input) {
        String s;
        String line;
        Book book = null;
        File f = new File(input);
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            while ((line = bf.readLine()) != null) {
                Matcher mt = ptn.matcher(line);
                if (mt.find()) {
                    s = mt.group(1);
                } else {
                    s = "";
                }
                switch (s) {
                    case "":
                        book = null;
                        break;
                    case "Book":
                        book = new Book();
                        books.add(book);
                        break;
                    case "Author":
                        if (book != null) {
                            book.addAuthor(mt.group(2));
                        }
                        break;
                    case "Title":
                        if (book != null) {
                            book.setTitle(mt.group(2));
                        }
                        break;
                    case "Publisher":
                        if (book != null) {
                            book.setPublisher(mt.group(2));
                        }
                        break;
                    case "Published":
                        if (book != null) {
                            book.setPublicationYear(Integer.parseInt(mt.group(2)));
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The text file could not be found. Please make sure it is there.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read line.");
            e.printStackTrace();
        }
        return books;
    }
}
