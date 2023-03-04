package com.bookstore.author.Mock;

import java.util.List;
import java.util.Random;

import com.bookstore.author.model.Author;

public class AuthorMock {

    private static Random rand = new Random();

    private static List<String> identifications = List.of("1751990332", "1751990333", "1751990334", "1751990335",
            "1751990336", "1751990337", "1751990338", "1751990339");
    private static List<String> names = List.of("David", "Daniel", "Dandre", "Tomas", "Jonh", "Juan", "Elian",
            "Marverick");
    private static List<String> lastnames = List.of("Muñoz", "Perez", "Galvis", "Espin", "Espinoza", "Cabascango",
            "Cruz", "Padilla");

    public static Author mockAuthor() {
        String identification = identifications.get(rand.nextInt(identifications.size()));
        String name = names.get(rand.nextInt(names.size()));
        String lastname = lastnames.get(rand.nextInt(lastnames.size()));
        return Author.builder()
                .identification(identification)
                .name(name)
                .lastname(lastname)
                .build();
    }

    public static List<Author> mockListOfAuthors() {
        return List.of(
                mockAuthor(),
                mockAuthor(),
                mockAuthor());
    }
}
