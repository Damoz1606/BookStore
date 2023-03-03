package com.bookstore.author.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.bookstore.author.controller.DTOs.ResponseAuthor;
import com.bookstore.author.controller.DTOs.RequestAuthor;
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
        List<ResponseAuthor> response = authors.stream().map(data -> AuthorMapper.map(data)).toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{identification}/{type}")
    public ResponseEntity<ResponseAuthor> findAuthorById(
            @PathVariable("type") String type,
            @PathVariable("identification") String identification) {
        Optional<Author> opt = this._service.findAuthorByPK(identification, type);
        if (opt.isPresent()) {
            return ResponseEntity.ok().body(AuthorMapper.map(opt.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity saveAuthor(
            @RequestBody RequestAuthor author) {
        if (this._service.SaveAuthor(AuthorMapper.map(author), author.getIdentification(),
                author.getIdentificationType())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{identification}/{type}")
    public ResponseEntity updateAuthor(
            @PathVariable("type") String type,
            @PathVariable("identification") String identification,
            @RequestBody RequestAuthor author) {
        if (this._service.UpdateAuthor(AuthorMapper.map(author), identification, type)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{identification}/{type}")
    public ResponseEntity deleteAuthor(
            @PathVariable("type") String type,
            @PathVariable("identification") String identification) {
        if (this._service.DeleteAuthor(identification, type)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
