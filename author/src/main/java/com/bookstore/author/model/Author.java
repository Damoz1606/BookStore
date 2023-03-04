package com.bookstore.author.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Include;

@Entity(name = "AUTHOR")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {

    @Id
    @Include
    @Column(name = "IDENTIFICATION", length = 15, nullable = false)
    private String identification;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;
    @Column(name = "LASTNAME", length = 32, nullable = false)
    private String lastname;
    @Column(name = "FULLNAME", length = 128, nullable = false)
    private String fullname;
    @Column(name = "CAREER", length = 32, nullable = true)
    private String career;
}
