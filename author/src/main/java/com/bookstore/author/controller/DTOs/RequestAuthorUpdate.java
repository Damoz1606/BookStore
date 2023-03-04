package com.bookstore.author.controller.DTOs;

import java.io.Serializable;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAuthorUpdate implements Serializable {

    private String name;
    private String lastname;
    private Optional<String> career;
}

