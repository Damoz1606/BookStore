package com.bookstore.author.controller.DTOs;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuthor implements Serializable{

    private String identification;
    private String identificationType;
    private String fullname;
    private String career;
}
