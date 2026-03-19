package com.estudosjavaspring.springcourse.resources;

import com.estudosjavaspring.springcourse.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @Test
    @DisplayName("It should return code 200 (Success) when listing users")
    void SuccessVerificationWhenListingUser() throws Exception{

        //ARRANGE

        //ACT
        var response = mockMvc.perform(
                get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERTIVE
        Assertions.assertEquals(200, response.getStatus());

        String content = response.getContentAsString();
        Assertions.assertFalse(content.isEmpty()); //Checking if it's empty
    }

    @Test
    @DisplayName("It should return code 200 (Success) when search user by Id")
    void SuccessVerificationWhenSearchingForUserByID() throws Exception{

        //ARRANGE
        String id = "20";

        //ACT
        var response = mockMvc.perform(
                get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERTIVE
        Assertions.assertEquals(200, response.getStatus());
    }

    void 
}