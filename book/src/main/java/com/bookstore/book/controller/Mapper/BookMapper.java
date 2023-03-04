package com.bookstore.book.controller.Mapper;

import java.util.List;

import com.bookstore.book.controller.DTOs.RequestBook;
import com.bookstore.book.controller.DTOs.ResponseBook;
import com.bookstore.book.controller.DTOs.ResponseBookAuthor;
import com.bookstore.book.model.Book;
import com.bookstore.book.model.Author;

public class BookMapper {

    public static ResponseBook map(Book book) {
        return ResponseBook.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    public static ResponseBookAuthor map(Book book, Author author) {
        return ResponseBookAuthor.builder()
                .title(book.getTitle())
                .authorFullname(author.getFullname())
                .build();
    }

    public static Book map(RequestBook book) {
        return Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    public static List<ResponseBook> map(List<Book> books) {
        return books.stream().map(data -> map(data)).toList();
    }
}
