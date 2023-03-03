package com.bookstore.author.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorPK implements Serializable{
    
    @Column(name = "IDENTIFICATION", length = 15, nullable = false)
    private String identification;
    @Column(name = "IDENTIFICATION_TYPE", length = 3, nullable = false)
    private String identificationType;
}
