package com.bookstore.book.controller.Mapper;

import com.bookstore.book.controller.DTOs.RequestBook;
import com.bookstore.book.controller.DTOs.ResponseBook;
import com.bookstore.book.model.Book;

public class BookMapper {

    public static ResponseBook map(Book book) {
        return ResponseBook.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    public static Book map(RequestBook book) {
        return Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }
}
