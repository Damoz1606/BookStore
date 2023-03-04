package com.bookstore.book.controller.DTOs;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBookAuthor implements Serializable {
    
    private String title;
    private String authorFullname;
}
