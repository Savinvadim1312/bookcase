package org.saxion.devtools.week1.bookcase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.saxion.devtools.week1.bookcase.model.Book;
import org.saxion.devtools.week1.bookcase.model.BookCase;
import org.saxion.devtools.week1.bookcase.model.exceptions.BookCaseOutOfRoomException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
       new Application().run(args);
    }

    public void run(String[] args) {
        System.out.println("Application has started!");
        System.out.println();

        String fileName =  getClass().getClassLoader().getResource("books.json").getPath();

        if(args.length> 0) {
            fileName = args[0];
        }

        List<Book> books = loadBooksFromFile(fileName);

        // Pretend this is the absolute best CLI interface you've ever seen...

        BookCase bookCase = new BookCase(books.size());

        try {
            for (Book b : books) {
                bookCase.addBook(b);
            }
        } catch (BookCaseOutOfRoomException bcoore) {
            bcoore.printStackTrace();
        }

        System.out.println(bookCase);

        System.out.println("Application finished!");
    }



    private static List<Book> loadBooksFromFile(String fileName) {
        ArrayList<Book> result = new ArrayList<>();

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONObject booksObject = new JSONObject(jsonString);
            
            JSONArray books = booksObject.getJSONArray("books");

            for (int i = 0; i < books.length(); i++) {
                JSONObject currentBookObject = books.getJSONObject(i);
                Book currentBook = new Book(currentBookObject);

                result.add(currentBook);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }
    
}