package com.bookstore.book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.bookstore.book.service.AuthorService;
import com.bookstore.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookstore.book.Mock.AuthorMock;
import com.bookstore.book.Mock.BookMock;
import com.bookstore.book.controller.DTOs.RequestBook;
import com.bookstore.book.controller.DTOs.ResponseBook;
import com.bookstore.book.controller.DTOs.ResponseBookAuthor;
import com.bookstore.book.controller.Mapper.BookMapper;
import com.bookstore.book.model.Author;
import com.bookstore.book.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc _mockMvc;

    @MockBean
    private BookService _bookService;

    @MockBean
    private AuthorService _authorService;

    @Autowired
    private ObjectMapper _mapper;

    @Test
    public void whenFindAllBooks_thenReturnOkStatusAndListOfResponseBook() throws Exception {
        // given
        List<Book> mock = BookMock.mockListOfBooks();
        List<ResponseBook> expected = BookMapper.map(mock);

        when(this._bookService.findAllBooks()).thenReturn(mock);
        // when
        ResultActions response = this._mockMvc.perform(get("/api/v1/books"));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(expected.size())))
                .andExpect(jsonPath("$[0].author", is(expected.get(0).getAuthor())))
                .andExpect(jsonPath("$[1].title", is(expected.get(1).getTitle())));
    }

    @Test
    public void whenFindAllBooks_thenReturnNoContentStatus() throws Exception {
        // given
        List<Book> mock = new ArrayList<>();

        when(this._bookService.findAllBooks()).thenReturn(mock);
        // when
        ResultActions response = this._mockMvc.perform(get("/api/v1/books"));

        // then
        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void whenFindBooksByAuthor_thenReturnOkStatusAndListOfResponseBook() throws Exception {
        List<Book> mockBooks = BookMock.mockListOfBooks();
        Author mockAuthor = AuthorMock.mockAuthor();
        String _id = "123457";

        List<ResponseBook> expected = BookMapper.map(mockBooks);

        when(this._bookService.findBooksByAuthor(any(String.class)))
                .thenReturn(mockBooks);

        ResultActions response = this._mockMvc.perform(get("/api/v1/books/author/{author}", _id));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(expected.size())))
                .andExpect(jsonPath("$[0].author", is(expected.get(0).getAuthor())))
                .andExpect(jsonPath("$[1].title", is(expected.get(1).getTitle())));

    }

    @Test
    public void whenFindBooksByAuthor_thenReturnNoContent() throws Exception {
        List<Book> mockBooks = new ArrayList<>();
        Author mockAuthor = AuthorMock.mockAuthor();
        String _id = "123457";

        when(this._bookService.findBooksByAuthor(any(String.class)))
                .thenReturn(mockBooks);

        ResultActions response = this._mockMvc.perform(get("/api/v1/books/author/{author}", _id));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void whenFindBookById_thenReturnOkStatusAndListOfResponseBookAuthor() throws Exception {
        Book book = BookMock.mockBook();
        Optional<Book> mockBook = Optional.of(book);
        Author mockAuthor = AuthorMock.mockAuthor();
        String _id = "123457";

        ResponseBookAuthor expected = BookMapper.map(book, mockAuthor);

        when(this._bookService.findBookById(any(String.class)))
                .thenReturn(mockBook);
        when(this._authorService.getAuthor(any(String.class)))
                .thenReturn(mockAuthor);

        ResultActions response = this._mockMvc.perform(get("/api/v1/books/book/{book}", _id));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.authorFullname", is(expected.getAuthorFullname())))
                .andExpect(jsonPath("$.title", is(expected.getTitle())));
    }

    @Test
    public void whenFindBookById_thenReturnNoContentStatus() throws Exception {
        Book book = BookMock.mockBook();
        Optional<Book> mockBook = Optional.empty();
        Author mockAuthor = AuthorMock.mockAuthor();
        String _id = "123457";

        when(this._bookService.findBookById(any(String.class)))
                .thenReturn(mockBook);
        when(this._authorService.getAuthor(any(String.class)))
                .thenReturn(mockAuthor);

        ResultActions response = this._mockMvc.perform(get("/api/v1/books/book/{book}", _id));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void whenSaveBook_thenReturnCreatedStatus() throws Exception {
        RequestBook body = RequestBook.builder()
                .author("Test Author")
                .title("Test Title")
                .build();
        boolean mock = true;

        when(this._bookService.saveBook(any(Book.class)))
                .thenReturn(mock);

        ResultActions response = this._mockMvc
                .perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this._mapper.writeValueAsBytes(body)));

        response.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void whenSaveBook_thenReturnInternalServerErrorStatus() throws Exception {
        RequestBook body = RequestBook.builder()
                .author("Test Author")
                .title("Test Title")
                .build();
        boolean mock = false;

        when(this._bookService.saveBook(any(Book.class)))
                .thenReturn(mock);

        ResultActions response = this._mockMvc
                .perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this._mapper.writeValueAsBytes(body)));

        response.andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    public void whenUpdateBook_thenReturnOkStatus() throws Exception {
        RequestBook body = RequestBook.builder()
                .author("Test Author")
                .title("Test Title")
                .build();
        boolean mock = true;
        String _id = "123457";

        when(this._bookService.updateBook(any(String.class), any(Book.class)))
                .thenReturn(mock);

        ResultActions response = this._mockMvc
                .perform(put("/api/v1/books/book/{book}", _id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this._mapper.writeValueAsBytes(body)));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenUpdateBook_thenReturnInternalServerErrorStatus() throws Exception {
        RequestBook body = RequestBook.builder()
                .author("Test Author")
                .title("Test Title")
                .build();
        boolean mock = false;
        String _id = "123457";

        when(this._bookService.updateBook(any(String.class), any(Book.class)))
                .thenReturn(mock);

        ResultActions response = this._mockMvc
                .perform(put("/api/v1/books/book/{book}", _id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this._mapper.writeValueAsBytes(body)));

        response.andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    public void whenDeleteBook_thenReturnOkStatus() throws Exception {
        boolean mock = true;
        String _id = "123457";

        when(this._bookService.deleteBook(any(String.class)))
                .thenReturn(mock);

        ResultActions response = this._mockMvc
                .perform(delete("/api/v1/books/book/{book}", _id));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenDeleteBook_thenReturnInternalServerErrorStatus() throws Exception {
        boolean mock = false;
        String _id = "123457";

        when(this._bookService.deleteBook(any(String.class)))
                .thenReturn(mock);

        ResultActions response = this._mockMvc
                .perform(delete("/api/v1/books/book/{book}", _id));

        response.andExpect(status().isInternalServerError())
                .andDo(print());
    }
}
