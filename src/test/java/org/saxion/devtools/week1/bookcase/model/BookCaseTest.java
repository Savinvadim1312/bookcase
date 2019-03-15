package org.saxion.devtools.week1.bookcase.model;

import org.json.JSONObject;
import org.junit.Test;
import org.saxion.devtools.week1.bookcase.model.exceptions.BookCaseOutOfRoomException;
import org.saxion.devtools.week1.bookcase.model.exceptions.BookNotFoundException;

import java.util.List;

import static org.junit.Assert.*;

public class BookCaseTest {

    JSONObject sampleBook = new JSONObject("{\n" +
            "        \"isbn\": \"9781593275846\",\n" +
            "        \"title\": \"Eloquent JavaScript, Second Edition\",\n" +
            "        \"subtitle\": \"A Modern Introduction to Programming\",\n" +
            "        \"author\": \"Marijn Haverbeke\",\n" +
            "        \"published\": \"2014-12-14T00:00:00.000Z\",\n" +
            "        \"publisher\": \"No Starch Press\",\n" +
            "        \"pages\": 472,\n" +
            "        \"description\": \"JavaScript lies at the heart of almost every modern " +
            "web application, from social apps to the newest browser-based games. Though " +
            "simple for beginners to pick up and play with, JavaScript is a flexible, " +
            "complex language that you can use to build full-scale applications.\",\n" +
            "        \"website\": \"http://eloquentjavascript.net/\"\n" +
            "      },");

    @Test
    public void addBookSuccesfull() throws BookCaseOutOfRoomException {
        BookCase bookCase = new BookCase(1);

        Book b = new Book(sampleBook);

        bookCase.addBook(b);
    }

    @Test(expected = BookCaseOutOfRoomException.class)
    public void addBookToFullCase() throws BookCaseOutOfRoomException {
        BookCase bookCase = new BookCase(0);

        Book b = new Book(sampleBook);

        bookCase.addBook(b);
    }

    @Test
    public void checkIfBookCaseHasSpace() {
        BookCase bookCase = new BookCase(2);

        Book b = new Book(sampleBook);

        assertTrue((bookCase.hasSpace()));
    }

    @Test
    public void findBookByAuthorExists() throws BookNotFoundException, BookCaseOutOfRoomException {
        BookCase bookCase = new BookCase(1);

        Book b = new Book(sampleBook);

        bookCase.addBook(b);

        List<Book> foundBooks = bookCase.findBooksByAuthor(b.getAuthor());

        assertEquals(foundBooks.size(), 1);
        assertEquals(foundBooks.get(0).getAuthor(), b.getAuthor());
    }

    @Test(expected = BookNotFoundException.class)
    public void findBookByAuthorNotExists() throws BookNotFoundException {
        BookCase bookCase = new BookCase(1);

        Book b = new Book(sampleBook);

        bookCase.findBooksByAuthor(b.getAuthor());
    }


    //    Added by us
        @Test
        public void findBookByTitle() throws BookNotFoundException, BookCaseOutOfRoomException {
            BookCase bookCase = new BookCase(1);

            Book b = new Book(sampleBook);

            bookCase.addBook(b);

            Book foundBook = bookCase.findBookByTitle(b.getTitle());

            assertEquals(foundBook.getTitle(), b.getTitle());
        }


//    This is added by us
    @Test
    public void testToString() throws BookNotFoundException, BookCaseOutOfRoomException {
        BookCase bookCase = new BookCase(1);
        Book b = new Book(sampleBook);
        bookCase.addBook(b);

        String string = bookCase.toString();

        assertNotEquals(string.length(), 0);
    }

    // Obviously we're far from done! But you get the idea..
}
