package com.bookstore.author;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bookstore.author.Mock.AuthorMock;
import com.bookstore.author.controller.Mapper.AuthorMapper;
import com.bookstore.author.model.Author;
import com.bookstore.author.services.AuthorService;
import com.bookstore.author.controller.DTOs.ResponseAuthor;
import com.bookstore.author.controller.DTOs.ResponseAuthorSingle;
import com.bookstore.author.controller.DTOs.RequestAuthor;
import com.bookstore.author.controller.DTOs.RequestAuthorUpdate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    private MockMvc _mockMvc;

    @MockBean
    private AuthorService _service;

    @Autowired
    private ObjectMapper _mapper;

    @Test
    public void whenFindAllAuthors_thenReturnOkStateAndListOfAuthors() throws Exception {
        // given
        List<Author> authors = AuthorMock.mockListOfAuthors();
        List<ResponseAuthor> expected = AuthorMapper.map(authors);

        when(this._service.findAllAuthors()).thenReturn(authors);
        // when
        ResultActions actual = this._mockMvc.perform(get("/api/v1/authors"));

        // then
        actual.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(expected.size())))
                .andExpect(jsonPath("$[0].fullname", is(expected.get(0).getFullname())))
                .andExpect(jsonPath("$[1].identification", is(expected.get(1).getIdentification())));
    }

    @Test
    public void whenFindAllAuthors_thenReturnNoContentState() throws Exception {
        // given
        List<Author> mock = new ArrayList<>();

        when(this._service.findAllAuthors()).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc.perform(get("/api/v1/authors"));

        // then
        actual.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void whenFindAuthorById_thenReturnOkStateAndResponseAuthorSingle() throws Exception {
        // given
        Author author = AuthorMock.mockAuthor();
        Optional<Author> mock = Optional.of(author);
        ResponseAuthorSingle expected = AuthorMapper.mapResponseAuthorSingle(author);
        String _id = "12345";

        when(this._service.findAuthorByPK(any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc.perform(get("/api/v1/authors/{identification}", _id));

        // then
        actual.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.fullname", is(expected.getFullname())))
                .andExpect(jsonPath("$.career", is(expected.getCareer())));
    }

    @Test
    public void whenFindAuthorById_thenReturnNoFoundState() throws Exception {
        // given
        Optional<Author> mock = Optional.empty();
        String _id = "12345";

        when(this._service.findAuthorByPK(any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc.perform(get("/api/v1/authors/{identification}", _id));

        // then
        actual.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void whenSaveAuthor_thenReturnOkState() throws Exception {
        // given
        Author auxAuthor = AuthorMock.mockAuthor();
        RequestAuthor body = RequestAuthor.builder()
                .name(auxAuthor.getName())
                .lastname(auxAuthor.getLastname())
                .identification(auxAuthor.getIdentification())
                .career(null)
                .identificationType("DNI")
                .build();

        boolean mock = true;

        when(this._service.SaveAuthor(any(Author.class), any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc
                .perform(post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this._mapper.writeValueAsBytes(body)));

        // then
        actual.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenSaveAuthor_thenReturnBadRequestState() throws Exception {
        // given
        Author auxAuthor = AuthorMock.mockAuthor();
        RequestAuthor author = RequestAuthor.builder()
                .name(auxAuthor.getName())
                .lastname(auxAuthor.getLastname())
                .identification(auxAuthor.getIdentification())
                .build();
        boolean mock = false;

        when(this._service.SaveAuthor(any(Author.class), any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this._mapper.writeValueAsBytes(author)));

        // then
        actual.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void whenUpdateAuthor_thenReturnOkState() throws Exception {
        // given
        Author auxAuthor = AuthorMock.mockAuthor();
        RequestAuthorUpdate body = RequestAuthorUpdate.builder()
                .name(auxAuthor.getName())
                .lastname(auxAuthor.getLastname())
                .build();

        boolean mock = true;

        when(this._service.UpdateAuthor(any(Author.class), any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc
                .perform(put("/api/v1/authors/{identification}", auxAuthor.getIdentification())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this._mapper.writeValueAsBytes(body)));

        // then
        actual.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenUpdateAuthor_thenReturnBadRequestState() throws Exception {
        // given
        Author auxAuthor = AuthorMock.mockAuthor();
        RequestAuthorUpdate body = RequestAuthorUpdate.builder()
                .name(auxAuthor.getName())
                .lastname(auxAuthor.getLastname())
                .build();

        boolean mock = false;

        when(this._service.UpdateAuthor(any(Author.class), any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc
                .perform(put("/api/v1/authors/{identification}", auxAuthor.getIdentification())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this._mapper.writeValueAsBytes(body)));

        // then
        actual.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void whenDeleteAuthor_thenReturnOkState() throws Exception {
        // given
        boolean mock = true;
        String _id = "12345";

        when(this._service.DeleteAuthor(any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc
                .perform(delete("/api/v1/authors/{identification}", _id));

        // then
        actual.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenDeleteAuthor_thenReturnBadRequestState() throws Exception {
        // given
        boolean mock = false;
        String _id = "12345";

        when(this._service.DeleteAuthor(any(String.class))).thenReturn(mock);
        // when
        ResultActions actual = this._mockMvc
                .perform(delete("/api/v1/authors/{identification}", _id));

        // then
        actual.andExpect(status().isBadRequest())
                .andDo(print());
    }
}
