package ex3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLibraryModel {

    private static final Pattern ptn = Pattern.compile("(\\w+):[ \\t]*([\\w\\s]*)");

    @Test
    public void testInitLocations() {
        Library library = new Library("myLib");
        String input = "./src/main/resources/ex3/init-library.txt";
        initLocations(library, input);
        assertEquals(3,library.getRoom("kids").rows.size());
        assertEquals(2,library.getRoom("teens").getRow("crime").shelfs.size());
    }

    private void initLocations(Library library, String inputFile ){
        String line;
        File f = new File(inputFile);
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            while ((line = bf.readLine()) != null) {
                String[] loc = line.split("/");
                if (!loc[0].isEmpty() && !library.roomExists(loc[0])){
                    library.addRoom(loc[0]);
                }
                if (!loc[1].isEmpty() && !library.getRoom(loc[0]).rowExists(loc[1])){
                    library.getRoom(loc[0]).addRow(loc[1]);
                }
                if (!loc[2].isEmpty() && !library.getRoom(loc[0]).getRow(loc[1]).shelfExists(loc[2])) {
                    library.getRoom(loc[0]).getRow(loc[1]).addShelf(loc[2]);
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("The text file could not be found. Please make sure it is there.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read line.");
            e.printStackTrace();
        }
    }

    @Test
    public void testAddBooks() {
        Library library = new Library("myLib");
        String input = "./src/main/resources/ex3/init-library.txt";
        initLocations(library, input);
        input = "./src/main/resources/ex3/books.txt";
        addBooks(library, input);
        assertEquals("benji", library.search("1234").getTitle());
    }

    public void addBooks(Library library, String inputFile) {
        String s;
        String line;
        File f = new File(inputFile);
        Book book = null;
        Tag tag = null;
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
                    case "book":
                        tag = new Tag();
                        book = new Book(tag);
                        break;
                    case "isbn":
                        if (book != null) {
                            book.setIsbn(mt.group(2));
                        }
                        break;
                    case "title":
                        if (tag != null) {
                            book.setTitle(mt.group(2));
                        }
                        break;
                    case "room":
                        if (tag != null) {
                            tag.setRoom(mt.group(2));
                        }
                        break;
                    case "row":
                        if (tag != null) {
                            tag.setRow(mt.group(2));
                        }
                        break;
                    case "shelf":
                        if (tag != null) {
                            tag.setShelf(mt.group(2));
                        }
                        break;
                    case "add":
                        library.addBook(book);
                        break;
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("The text file could not be found. Please make sure it is there.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read line.");
            e.printStackTrace();
        }
    }
}