package org.saxion.devtools.week1.bookcase.model;

import org.saxion.devtools.week1.bookcase.model.exceptions.BookCaseOutOfRoomException;
import org.saxion.devtools.week1.bookcase.model.exceptions.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BookCase {

    private ArrayList<Book> books;
    private int limit;

    public BookCase(int limit) {
        this.limit = limit;

        this.books = new ArrayList<>();
    }

    public boolean hasSpace() {
        return books.size() < limit;
    }

    public void addBook(Book newBook) throws BookCaseOutOfRoomException {
        if(books.size() < limit) {
            books.add(newBook);
        } else {
            throw new BookCaseOutOfRoomException();
        }
    }

    public Book findBookByTitle(String title) throws BookNotFoundException {
        for(Book b : books) {
            if(b.getTitle().equalsIgnoreCase(title) || b.getTitle().equalsIgnoreCase(title) || b.getTitle().toString() == title.toString()) {
                return b;
            }
        }

        throw new BookNotFoundException("There is no book matching title: " + title);
    }

    public void removeBookByTitle(String title) throws BookNotFoundException {
        books.remove(findBookByTitle(title));
    }

    public List<Book> findBooksByAuthor(String authorName) throws BookNotFoundException {
        ArrayList<Book> result = new ArrayList<>();

        for(Book b : books) {
            if(b.getAuthor().equalsIgnoreCase(authorName)) {
                result.add(b);
            }
        }

        if(result.size() > 0) {
            return result;
        } else {

            throw new BookNotFoundException("There is no book with author: " + authorName);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Book b : books) {
            result.append(b);
            result.append('\n');
        }

        return result.toString();
    }
}
