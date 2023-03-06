package com.bookstore.author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        // given
        List<Author> mock = AuthorMock.mockListOfAuthors();
        List<Author> expected = mock;

        when(this._respository.findAll()).thenReturn(mock);

        // when
        List<Author> actual = this._service.findAllAuthors();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void whenFindAuthorByPK_thenReturnOptionalOfAuthor() {
        Author author = AuthorMock.mockAuthor();
        Optional<Author> mock = Optional.of(author);
        Optional<Author> expected = Optional.of(author);
        String _id = "1751990332";

        when(this._respository.findById(any(String.class)))
                .thenReturn(mock);

        Optional<Author> actual = this._service.findAuthorByPK(_id);

        assertEquals(expected, actual);
    }

    @Test
    public void whenSaveAuthor_thenReturnTrue() {
        ArgumentCaptor<Author> argument = ArgumentCaptor.forClass(Author.class);
        Author author = AuthorMock.mockAuthor();
        Optional<Author> mock = Optional.empty();
        boolean expected = true;
        String _id = "1751990332";

        when(this._respository.findById(any(String.class)))
                .thenReturn(mock);

        boolean actual = this._service.SaveAuthor(author, _id);

        verify(this._respository).save(argument.capture());
        assertEquals(expected, actual);
        assertEquals(author, argument.getValue());
    }

    @Test
    public void whenSaveAuthor_thenReturnFalse() {
        Author author = AuthorMock.mockAuthor();
        Optional<Author> mock = Optional.of(author);
        boolean expected = false;
        String _id = "1751990332";

        when(this._respository.findById(any(String.class)))
                .thenReturn(mock);

        boolean actual = this._service.SaveAuthor(author, _id);

        assertEquals(expected, actual);
    }

    @Test
    public void whenUpdateAuthor_thenReturnTrue() {
        ArgumentCaptor<Author> argument = ArgumentCaptor.forClass(Author.class);
        Author author = AuthorMock.mockAuthor();
        Optional<Author> mock = Optional.of(author);
        boolean expected = true;
        String _id = "1751990332";

        when(this._respository.findById(any(String.class)))
                .thenReturn(mock);

        boolean actual = this._service.UpdateAuthor(author, _id);

        verify(this._respository).save(argument.capture());
        assertEquals(expected, actual);
        assertEquals(author, argument.getValue());
    }

    @Test
    public void whenUpdateAuthor_thenReturnFalse() {
        Author author = AuthorMock.mockAuthor();
        Optional<Author> mock = Optional.empty();
        boolean expected = false;
        String _id = "1751990332";

        when(this._respository.findById(any(String.class)))
                .thenReturn(mock);

        boolean actual = this._service.UpdateAuthor(author, _id);

        assertEquals(expected, actual);
    }

    @Test
    public void whenDeleteAuthor_thenReturnTrue() {
        ArgumentCaptor<Author> argument = ArgumentCaptor.forClass(Author.class);
        Author author = AuthorMock.mockAuthor();
        Optional<Author> mock = Optional.of(author);
        boolean expected = true;
        String _id = "1751990332";

        when(this._respository.findById(any(String.class)))
                .thenReturn(mock);

        boolean actual = this._service.DeleteAuthor(_id);

        verify(this._respository).delete(argument.capture());
        assertEquals(expected, actual);
        assertEquals(author, argument.getValue());
    }

    @Test
    public void whenDeleteAuthor_thenReturnFalse() {
        Optional<Author> mock = Optional.empty();
        boolean expected = false;
        String _id = "1751990332";

        when(this._respository.findById(any(String.class)))
                .thenReturn(mock);

        boolean actual = this._service.DeleteAuthor(_id);

        assertEquals(expected, actual);
    }

}
