package com.bookstore.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book.controller.DTOs.RequestBook;
import com.bookstore.book.controller.DTOs.ResponseBook;
import com.bookstore.book.controller.DTOs.ResponseBookAuthor;
import com.bookstore.book.controller.Mapper.BookMapper;
import com.bookstore.book.model.Author;
import com.bookstore.book.model.Book;
import com.bookstore.book.service.AuthorService;
import com.bookstore.book.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService _service;
    private final AuthorService _author;

    public BookController(BookService _service, AuthorService _author) {
        this._service = _service;
        this._author = _author;
    }

    @GetMapping
    public ResponseEntity<List<ResponseBook>> findAllBooks() {
        List<Book> books = this._service.findAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ResponseBook> responseBooks = books.stream().map(data -> BookMapper.map(data)).toList();
        return ResponseEntity.ok().body(responseBooks);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<ResponseBook>> findBooksByAuthor(
            @PathVariable("author") String author) {
        List<Book> books = this._service.findBooksByAuthor(author);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ResponseBook> responseBooks = books.stream().map(data -> BookMapper.map(data)).toList();
        return ResponseEntity.ok().body(responseBooks);
    }

    @GetMapping("/book/{book}")
    public ResponseEntity<ResponseBookAuthor> findBookById(
            @PathVariable("book") String id) {
        Optional<Book> book = this._service.findBookById(id);
        if (book.isPresent()) {
            Author author = this._author.getAuthor(book.get().getAuthor());
            if(!author.equals(null)) {
                ResponseBookAuthor responseBook = BookMapper.map(book.get(), author);
                return ResponseEntity.ok().body(responseBook);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity saveBook(
            @RequestBody RequestBook book) {
        boolean flag = this._service.saveBook(BookMapper.map(book));
        if (flag) {
            return ResponseEntity.created(null).build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/book/{book}")
    public ResponseEntity updateBook(
            @PathVariable("book") String id,
            @RequestBody RequestBook book) {
        boolean flag = this._service.updateBook(id, BookMapper.map(book));
        if (flag) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/book/{book}")
    public ResponseEntity deleteBook(
            @PathVariable("book") String id) {
        boolean flag = this._service.deleteBook(id);
        if (flag) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
