package com.sskorupski.learn.springboot.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    private static final String USERS_PATH = "/users/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @DisplayName("GET /users/ : WHILE no users is found SHOULD return HTTP 204 AND have empty array body")
    @Test
    public void getAll_shouldReturn_HttpNoContent_withEmptyArrayBody() throws Exception {
        // GIVEN
        when(userService.getAll()).thenReturn(new ArrayList<>());
        // WHEN
        this.mockMvc.perform(get(USERS_PATH))
                // THEN
                .andExpect(status().isNoContent())
                .andExpect(content().string(is(equalTo("[]"))));
    }

    @DisplayName("GET /users/ : WHILE users is found SHOULD return HTTP 200 AND have not empty body")
    @Test
    public void getAll_shouldReturn_HttpOk_withNotEmptyBody() throws Exception {
        // GIVEN
        when(userService.getAll()).thenReturn(List.of(
                new User(1L, "expectedUsername", "password", "email")
        ));
        // WHEN
        this.mockMvc.perform(get(USERS_PATH))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("expectedUsername")));
    }
}
