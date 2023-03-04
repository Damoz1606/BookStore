package com.bookstore.author.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstore.author.model.Author;
import com.bookstore.author.repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository _repository;

    public AuthorService(AuthorRepository _repository) {
        this._repository = _repository;
    }

    public List<Author> findAllAuthors() {
        return this._repository.findAll();
    }

    public Optional<Author> findAuthorByPK(String identification) {
        return this._repository
                .findById(identification);
    }

    public boolean SaveAuthor(Author author, String identification) {
        Optional<Author> opt = this.findAuthorByPK(identification);
        if (!opt.isPresent()) {
            author.setIdentification(identification);
            this._repository.save(author);
            return true;
        } else {
            return false;
        }
    }

    public boolean UpdateAuthor(Author author, String identification) {
        Optional<Author> opt = this.findAuthorByPK(identification);
        if (opt.isPresent()) {
            author.setIdentification(identification);
            this._repository.save(author);
            return true;
        } else {
            return false;
        }
    }

    public boolean DeleteAuthor(String identification) {
        Optional<Author> opt = this.findAuthorByPK(identification);
        if (opt.isPresent()) {
            this._repository.delete(opt.get());
            return true;
        } else {
            return false;
        }
    }
}
