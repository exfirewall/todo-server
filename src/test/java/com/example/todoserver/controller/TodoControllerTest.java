package com.example.todoserver.controller;

import com.example.todoserver.model.TodoEntity;
import com.example.todoserver.model.TodoReq;
import com.example.todoserver.model.TodoRes;
import com.example.todoserver.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private TodoService todoService;

    private TodoEntity expected;

    @BeforeEach
    void setup(){
        this.expected = new TodoEntity();
        this.expected.setId(123L);
        this.expected.setTitle("test title");
        this.expected.setOrder(0L);
        this.expected.setCompleted(false);
    }
    @Test
    void create() throws Exception {
        when(this.todoService.add(any(TodoReq.class)))
                .then((i)->{
                    TodoReq reqquest = i.getArgument(0,TodoReq.class);
                    return new TodoEntity(this.expected.getId(),
                            reqquest.getTitle(),
                            this.expected.getOrder(),
                            this.expected.getCompleted());
                });

        TodoReq request = new TodoReq();
        request.setTitle("ANY TITLE");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        this.mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
        .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("ANY TITLE"));

    }

    @Test
    void readOne() {
    }
}