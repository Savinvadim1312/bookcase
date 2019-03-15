package org.saxion.devtools.week1.bookcase.model;

import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Book {

    private String isbn;
    private String title;
    private String subtitle;
    private String author;
    private LocalDate published;
    private String publisher;
    private int pages;
    private String description;
    private String website;
    private String unused;

    public Book(JSONObject jsonObject) {
        this.isbn = jsonObject.getString("isbn");
        this.title = jsonObject.getString("title");
        this.subtitle = jsonObject.getString("subtitle");
        this.author = jsonObject.getString("author");
        this.publisher = jsonObject.getString("publisher");
        this.pages = jsonObject.getInt("pages");
        this.description = jsonObject.getString("description");
        this.website = jsonObject.getString("website");

        // Some dirty tricks to convert the published date.
        Instant instant = Instant.parse(jsonObject.getString("published"));
        this.published = LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate();
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", author='" + author + '\'' +
                ", published=" + published +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public String getTitle() {
        return title.toString();
    }

    public String getAuthor() {
        return author;
    }
}
