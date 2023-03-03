package com.bookstore.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.author.model.Author;
import com.bookstore.author.model.AuthorPK;

@Repository
public interface AuthorRepository extends JpaRepository<Author, AuthorPK>{
    
}
