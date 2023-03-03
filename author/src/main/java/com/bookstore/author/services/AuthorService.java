package com.bookstore.author.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstore.author.model.Author;
import com.bookstore.author.model.AuthorPK;
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

    public Optional<Author> findAuthorByPK(String identification, String type) {
        return this._repository
                .findById(AuthorPK.builder().identification(identification).identificationType(type).build());
    }

    public Optional<Author> findAuthorByPK(AuthorPK pk) {
        return this._repository
                .findById(pk);
    }

    public boolean SaveAuthor(Author author, String identification, String type) {
        return this.SaveAuthor(author,
                AuthorPK.builder().identification(identification).identificationType(type).build());
    }

    public boolean SaveAuthor(Author author, AuthorPK pk) {
        Optional<Author> opt = this.findAuthorByPK(pk);
        if (!opt.isPresent()) {
            author.setPk(pk);
            this._repository.save(author);
            return true;
        } else {
            return false;
        }
    }

    public boolean UpdateAuthor(Author author, String identification, String type) {
        return this.UpdateAuthor(author,
                AuthorPK.builder().identification(identification).identificationType(type).build());
    }

    public boolean UpdateAuthor(Author author, AuthorPK pk) {
        Optional<Author> opt = this.findAuthorByPK(pk);
        if (opt.isPresent()) {
            this._repository.save(author);
            return true;
        } else {
            return false;
        }
    }

    public boolean DeleteAuthor(String identification, String type) {
        return this.DeleteAuthor(AuthorPK.builder().identification(identification).identificationType(type).build());
    }

    public boolean DeleteAuthor(AuthorPK pk) {
        Optional<Author> opt = this.findAuthorByPK(pk);
        if (opt.isPresent()) {
            this._repository.delete(opt.get());
            return true;
        } else {
            return false;
        }
    }
}
