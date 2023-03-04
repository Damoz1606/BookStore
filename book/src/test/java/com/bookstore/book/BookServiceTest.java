package com.bookstore.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
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
        //given
        List<Book> mock = BookMock.mockListOfBooks();
        List<Book> expected = mock;

        Mockito.when(this._repository.findAll()).thenReturn(mock);
        //when
        List<Book> actual = this._service.findAllBooks();
        //then
        assertEquals(expected, actual);
    }
}
