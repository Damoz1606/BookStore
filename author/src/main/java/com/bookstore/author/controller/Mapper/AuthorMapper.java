package com.bookstore.author.controller.Mapper;

import com.bookstore.author.controller.DTOs.RequestAuthor;
import com.bookstore.author.controller.DTOs.ResponseAuthor;
import com.bookstore.author.model.Author;
import com.bookstore.author.model.AuthorPK;

public class AuthorMapper {

    public static ResponseAuthor map(Author author) {
        return ResponseAuthor.builder()
                .identification(author.getPk().getIdentification())
                .identificationType(author.getPk().getIdentificationType())
                .fullname(author.getFullname())
                .career(author.getCareer())
                .build();
    }

    public static Author map(RequestAuthor author) {
        return Author.builder()
                .pk(AuthorPK.builder().identification(author.getIdentification())
                        .identificationType(author.getIdentificationType()).build())
                .name(author.getName())
                .lastname(author.getLastname())
                .fullname(author.getName() + " " + author.getLastname())
                .career(author.getCareer().get())
                .build();
    }
}
