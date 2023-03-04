package com.bookstore.author;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bookstore.author.Mock.AuthorMock;
import com.bookstore.author.controller.DTOs.ResponseAuthor;
import com.bookstore.author.controller.Mapper.AuthorMapper;
import com.bookstore.author.model.Author;
import com.bookstore.author.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorController {
    
    @Autowired
    private MockMvc _mockMvc;

    @MockBean
    private AuthorService _service;

    @Autowired
    private ObjectMapper _mapper;

    @Test
    public void whenFindAllAuthors_thenReturnListOfAuthors() throws Exception {
        //given
        List<Author> authors = AuthorMock.mockListOfAuthors();
        List<ResponseAuthor> expected = AuthorMapper.map(authors);

        when(this._service.findAllAuthors()).thenReturn(authors);
        //when
        ResultActions actual = this._mockMvc.perform(get("/api/v1/authors"));
        
        //then
        actual.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()", is(expected.size())))
        .andExpect(jsonPath("$[0].fullname", is(expected.get(0).getFullname())))
        .andExpect(jsonPath("$[1].identification", is(expected.get(1).getIdentification())));
    }
}
