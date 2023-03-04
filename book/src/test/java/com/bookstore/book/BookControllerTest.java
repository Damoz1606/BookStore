package com.bookstore.book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.bookstore.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookstore.book.Mock.BookMock;
import com.bookstore.book.controller.BookController;
import com.bookstore.book.controller.DTOs.ResponseBook;
import com.bookstore.book.controller.Mapper.BookMapper;
import com.bookstore.book.model.Book;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc _mockMvc;

    @MockBean
    private BookService _service;

    @Autowired
    private ObjectMapper _mapper;
    
    @Test
    public void whenFindAllBooks_thenReturnListOfResponseBook() throws Exception {
        //given
        List<Book> mock = BookMock.mockListOfBooks();
        List<ResponseBook> expected = BookMapper.map(mock);

        when(this._service.findAllBooks()).thenReturn(mock);
        //when
        ResultActions response = this._mockMvc.perform(get("/api/v1/books"));
        //then

        response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()", is(expected.size())))
        .andExpect(jsonPath("$[0].author", is(expected.get(0).getAuthor())))
        .andExpect(jsonPath("$[1].title", is(expected.get(1).getTitle())));
    }
}
