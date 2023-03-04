package com.bookstore.author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import com.bookstore.author.services.AuthorService;
import com.bookstore.author.Mock.AuthorMock;
import com.bookstore.author.repository.AuthorRepository;
import com.bookstore.author.model.Author;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorServiceTest {

    @Mock
    public AuthorRepository _respository;

    @InjectMocks
    public AuthorService _service;

    @Test
    public void whenFindAllAuthors_thenReturnListOfAuthors() {
        //given
        List<Author> mock = AuthorMock.mockListOfAuthors();
        List<Author> expected = mock;

        when(this._respository.findAll()).thenReturn(mock);

        //when
        List<Author> actual = this._service.findAllAuthors();

        //then
        assertEquals(expected, actual);
    }
}
