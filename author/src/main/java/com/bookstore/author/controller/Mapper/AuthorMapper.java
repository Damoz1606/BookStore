package com.bookstore.author.controller.Mapper;

import java.util.List;

import com.bookstore.author.controller.DTOs.RequestAuthor;
import com.bookstore.author.controller.DTOs.RequestAuthorUpdate;
import com.bookstore.author.controller.DTOs.ResponseAuthor;
import com.bookstore.author.controller.DTOs.ResponseAuthorSingle;
import com.bookstore.author.model.Author;

public class AuthorMapper {

    public static ResponseAuthor mapResponseAuthor(Author author) {
        return ResponseAuthor.builder()
                .identification(author.getIdentification())
                .fullname(author.getFullname())
                .career(author.getCareer())
                .build();
    }

    public static Author mapAuthor(RequestAuthorUpdate author) {
        return Author.builder()
                .name(author.getName())
                .lastname(author.getLastname())
                .fullname(author.getName() + " " + author.getLastname())
                .career(author.getCareer().get())
                .build();
    }

    public static Author mapAuthor(RequestAuthor author) {
        return Author.builder()
                .identification(author.getIdentification())
                .name(author.getName())
                .lastname(author.getLastname())
                .fullname(author.getName() + " " + author.getLastname())
                .career(author.getCareer().get())
                .build();
    }
    
    public static ResponseAuthorSingle mapResponseAuthorSingle(Author author) {
        return ResponseAuthorSingle.builder()
                .fullname(author.getFullname())
                .career(author.getCareer())
                .build();
    }

    public static List<ResponseAuthor> map(List<Author> authors) {
        return authors.stream().map(data -> mapResponseAuthor(data)).toList();
    }
}
