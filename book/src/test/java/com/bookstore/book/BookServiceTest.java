package com.bookstore.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.book.model.Book;
import com.bookstore.book.Mock.BookMock;
import com.bookstore.book.respository.BookRepository;
import com.bookstore.book.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTest {

    @Mock
    private BookRepository _repository;

    @InjectMocks
    private BookService _service;

    @Test
    public void whenFindAllBooks_thenReturnListOfBooks() {
        // given
        List<Book> mock = BookMock.mockListOfBooks();
        List<Book> expected = mock;

        Mockito.when(this._repository.findAll()).thenReturn(mock);
        // when
        List<Book> actual = this._service.findAllBooks();
        // then
        assertEquals(expected, actual);
    }

    @Test
    public void whenFindBookById_thenReturnOptionalBook() {
        Book book = BookMock.mockBook();
        Optional<Book> mock = Optional.of(book);
        Optional<Book> expected = Optional.of(book);

        Mockito.when(this._repository.findById(any(String.class))).thenReturn(mock);

        Optional<Book> actual = this._service.findBookById("1");

        assertEquals(expected, actual);
    }

    @Test
    public void whenFindBooksByAuthor_thenReturnListOfBooks() {
        List<Book> mock = BookMock.mockListOfBooks();
        List<Book> expected = mock;

        Mockito.when(this._repository.findByAuthor(any(String.class))).thenReturn(mock);

        List<Book> actual = this._service.findBooksByAuthor("111");

        assertEquals(expected, actual);
    }

    @Test
    public void whenSaveBook_thenReturnTrue() {
        ArgumentCaptor<Book> argument = ArgumentCaptor.forClass(Book.class);
        Book paramBook = BookMock.mockBook();
        List<Book> mockBooks = BookMock.mockListOfBooks();
        boolean expected = true;
        paramBook.setTitle("Titulo Prueba");

        Mockito.when(this._repository
                .findByAuthor(any(String.class)))
                .thenReturn(mockBooks);

        boolean actual = this._service.saveBook(paramBook);

        verify(this._repository).save(argument.capture());
        assertEquals(expected, actual);
        assertEquals(paramBook, argument.getValue());
    }

    @Test
    public void whenSaveBook_thenReturnFalse() {
        Book paramBook = BookMock.mockBook();
        boolean expected = false;
        List<Book> mockBooks = new ArrayList<>();

        mockBooks.add(paramBook);
        mockBooks.add(BookMock.mockBook());
        mockBooks.add(BookMock.mockBook());
        mockBooks.add(BookMock.mockBook());

        Mockito.when(this._repository
                .findByAuthor(any(String.class)))
                .thenReturn(mockBooks);

        boolean actual = this._service.saveBook(paramBook);

        assertEquals(expected, actual);
    }

    @Test
    public void whenUpdateBook_thenReturnTrue() {
        ArgumentCaptor<Book> argument = ArgumentCaptor.forClass(Book.class);
        Book book = BookMock.mockBook();
        Optional<Book> mockOptionalBook = Optional.of(book);
        boolean expected = true;

        book.setTitle("Test Title");

        Mockito.when(this._repository
                .findById(any(String.class)))
                .thenReturn(mockOptionalBook);

        boolean actual = this._service.updateBook(book.getId(), book);

        verify(this._repository).save(argument.capture());
        assertEquals(expected, actual);
        assertEquals(book, argument.getValue());
    }

    @Test
    public void whenUpdateBook_thenReturnFalse() {
        Book book = BookMock.mockBook();
        Optional<Book> mockOptionalBook = Optional.empty();
        boolean expected = false;

        book.setTitle("Test Title");

        Mockito.when(this._repository
                .findById(any(String.class)))
                .thenReturn(mockOptionalBook);

        boolean actual = this._service.updateBook(book.getId(), book);

        assertEquals(expected, actual);
    }

    @Test
    public void whenDeleteBook_thenReturnTrue() {
        Book book = BookMock.mockBook();
        Optional<Book> mockOptionalBook = Optional.of(book);
        boolean expected = true;

        book.setTitle("Test Title");

        Mockito.when(this._repository
                .findById(any(String.class)))
                .thenReturn(mockOptionalBook);

        boolean actual = this._service.deleteBook(book.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void whenDeleteBook_thenReturnFalse() {
        Book book = BookMock.mockBook();
        Optional<Book> mockOptionalBook = Optional.empty();
        boolean expected = false;

        book.setTitle("Test Title");

        Mockito.when(this._repository
                .findById(any(String.class)))
                .thenReturn(mockOptionalBook);

        boolean actual = this._service.deleteBook(book.getId());

        assertEquals(expected, actual);
    }
}
