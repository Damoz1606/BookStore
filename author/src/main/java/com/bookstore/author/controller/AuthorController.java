package com.bookstore.author.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.bookstore.author.controller.DTOs.ResponseAuthor;
import com.bookstore.author.controller.DTOs.ResponseAuthorSingle;
import com.bookstore.author.controller.DTOs.RequestAuthor;
import com.bookstore.author.controller.DTOs.RequestAuthorUpdate;
import com.bookstore.author.controller.Mapper.AuthorMapper;
import com.bookstore.author.model.Author;
import com.bookstore.author.services.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService _service;

    public AuthorController(AuthorService _service) {
        this._service = _service;
    }

    @GetMapping
    public ResponseEntity<List<ResponseAuthor>> findAllAuthors() {
        List<Author> authors = this._service.findAllAuthors();
        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ResponseAuthor> response = authors.stream().map(data -> AuthorMapper.mapResponseAuthor(data)).toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{identification}")
    public ResponseEntity<ResponseAuthorSingle> findAuthorById(
            @PathVariable("identification") String identification) {
        Optional<Author> opt = this._service.findAuthorByPK(identification);
        if (opt.isPresent()) {
            return ResponseEntity.ok().body(AuthorMapper.mapResponseAuthorSingle(opt.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveAuthor(
            @RequestBody RequestAuthor author) {
        if (this._service.SaveAuthor(AuthorMapper.mapAuthor(author), author.getIdentification())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{identification}")
    public ResponseEntity<Object> updateAuthor(
            @PathVariable("identification") String identification,
            @RequestBody RequestAuthorUpdate author) {
        if (this._service.UpdateAuthor(AuthorMapper.mapAuthor(author), identification)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<Object> deleteAuthor(
            @PathVariable("identification") String identification) {
        if (this._service.DeleteAuthor(identification)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
