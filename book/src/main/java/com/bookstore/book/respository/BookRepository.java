package com.bookstore.book.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.book.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByAuthor(String author);
}
