package com.bookstore.author.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

    @EmbeddedId
    @Include
    private AuthorPK pk;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;
    @Column(name = "LASTNAME", length = 32, nullable = false)
    private String lastname;
    @Column(name = "FULLNAME", length = 128, nullable = false)
    private String fullname;
    @Column(name = "CAREER", length = 32, nullable = true)
    private String career;
}
