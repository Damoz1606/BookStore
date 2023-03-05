package com.bookstore.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstore.book.model.Book;
import com.bookstore.book.respository.BookRepository;

@Service
public class BookService {

    private final BookRepository _repository;

    public BookService(BookRepository _repository) {
        this._repository = _repository;
    }

    public List<Book> findAllBooks() {
        return this._repository.findAll();
    }

    public Optional<Book> findBookById(String id) {
        return this._repository.findById(id);
    }

    public List<Book> findBooksByAuthor(String author) {
        return this._repository.findByAuthor(author);
    }

    public boolean saveBook(Book book) {
        List<Book> books = this.findBooksByAuthor(book.getAuthor());
        List<Book> sameTitleBooks = books.stream().filter(data -> data.getTitle().equals(book.getTitle())).toList();
        if (books.isEmpty() || sameTitleBooks.isEmpty()) {
            this._repository.save(book);
            return true;
        }
        return false;
    }

    public boolean updateBook(String id, Book book) {
        Optional<Book> opt = this.findBookById(id);
        if (opt.isPresent()) {
            Book actualBook = opt.get();
            actualBook.setAuthor(book.getAuthor());
            actualBook.setTitle(book.getTitle());
            this._repository.save(actualBook);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteBook(String id) {
        Optional<Book> opt = this.findBookById(id);
        if (opt.isPresent()) {
            this._repository.delete(opt.get());
            return true;
        } else {
            return false;
        }
    }
}
