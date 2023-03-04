package com.bookstore.book.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.bookstore.book.model.Author;

@Service
public class AuthorService {

    private RestTemplate rest = new RestTemplate();

    public Author getAuthor(String identification) {
        String url = "http://author:8082/api/v1/authors/{identification}";

        ResponseEntity<Author> response = rest.getForEntity(
                url,
                Author.class,
                identification);

        if (response.getStatusCode().is2xxSuccessful()) {
            return new ObjectMapper().convertValue(response.getBody(), Author.class);
        }
        return null;
    }
}
