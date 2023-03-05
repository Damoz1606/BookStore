package com.bookstore.book.Mock;

import java.util.List;
import java.util.Random;

import com.bookstore.book.model.Author;

public class AuthorMock {

    private static Random rand = new Random();

    private static List<String> careers = List.of("Writer", "Software Engineer", "Architect", "Soccer Player",
            "Salesman", "Administrator", "Marketing",
            "Teacher");

    private static List<String> authors = List.of("Garcia Marquez", "Paul Sepulveda", "J.K Rowling",
            "Miguel de Cervantes", "Dr.Zeus", "Julio Verne",
            "Michael Crichton", "James Dashner");

    public static Author mockAuthor() {
        String author = authors.get(rand.nextInt(authors.size()));
        String career = careers.get(rand.nextInt(careers.size()));

        return Author.builder()
                .fullname(author)
                .career(career)
                .build();
    }

}
