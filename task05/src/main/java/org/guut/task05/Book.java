package org.guut.task05;

import java.util.ArrayList;

public class Book {
    private ArrayList<Author> authors;
    private String title;

    /**
     * Creates a new book. Authors must be added through the addAuthor() method.
     * Also initializes the authorList with a size of two.
     * @param title the title of the book
     */
    public Book(String title) {
        this.title = title;
        authors  = new ArrayList<>(2);
    }

    /**
     *
     * @return a ArrayList<Author> with all the authors of the book.
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Gets a string representation of the books title
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Adds an author to the book, returns the index of the new author
     * @param author an author-object
     * @return returns the index of the new author
     */
    public int addAuthor(Author author){
        authors.add(author);
        return authors.indexOf(author);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return title.equals(book.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
