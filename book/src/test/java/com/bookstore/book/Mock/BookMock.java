package com.bookstore.book.Mock;

import java.util.List;
import java.util.Random;

import com.bookstore.book.model.Book;

public class BookMock {

    private static Random rand = new Random();

    private static List<String> ids = List.of("1", "2", "3", "4",
            "5", "6", "7", "8");
    private static List<String> titles = List.of("Harry Potter", "Gulliver", "Jurassic Park", "Maze Runner", "Elaina's Jouney", "Kono Subarashi sekai ni Shukufuko wo", "Dr. Jekyll and Mr.Hide",
            "Cien años de Soledad");
    private static List<String> authors = List.of("Garcia Marquez", "Paul Sepulveda", "J.K Rowling", "Miguel de Cervantes", "Dr.Zeus", "Julio Verne",
            "Michael Crichton", "James Dashner");
    
    public static Book mockBook() {
        String id = ids.get(rand.nextInt(ids.size()));
        String title = titles.get(rand.nextInt(titles.size()));
        String author = authors.get(rand.nextInt(authors.size()));
        return Book.builder()
        .id(id)
        .title(title)
        .author(author)
        .build();
    }

    public static List<Book> mockListOfBooks() {
        return List.of(
                mockBook(),
                mockBook(),
                mockBook()
        );
    }
    
}
