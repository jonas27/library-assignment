package ex2;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private List<String> authors;
    private String title;
    private String publisher;
    private int publicationYear;

    public Book() {
        authors = new ArrayList();
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String author) {
        authors.add(author);
    }

    public void removeAuthor(String author) {
        this.authors.remove(author);
    }

    public void removeAuthor(int position) {
        this.authors.remove(position);
    }

    public void removeAuthors() {
        this.authors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}


