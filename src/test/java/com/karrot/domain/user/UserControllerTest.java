package com.karrot.domain.user;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.cfg.NotYetImplementedException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.InputStream;
import com.fasterxml.jackson.databind.JsonNode;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    //TODO: service 구현 이후에는 의존성 mock 객체 주입후 테스트
    @MockBean
    private UserController userController;

    @MockBean
    private UserService userService;

    //TODO: OpenAPI 스펙에 대한 단위테스트
    /**
     * name : givenSwaggerDocs_whenCreateUser_thenShouldIncludeEssentialFields
     * desc : CreateUser OpenAPI 문서에 필수 필드가 포함되어야 함
     *        - summary, description
        HttpClient httpclient = HttpClients.createDefault();
     */
    @Test
    public void givenSwaggerDocs_whenCreateUser_thenShouldIncludeEssentialFields() throws Exception {
        //given
        HttpClient httpclient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api-docs"))
            .build();
        HttpResponse<InputStream> response = httpclient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        InputStream json = response.body();
        JsonNode swagger = new ObjectMapper().readTree(json);

        //when and then
        assertEquals("Create User", swagger.get("paths").get("/api/users/").get("post").get("summary").asText());

    }


    // TODO: Controller 구현 로직에 대한 단위테스트
    // TODO: [착안] swagger docs, unittest docs 어느쪽이 좋을까? https://jaeseo0519.tistory.com/406
    /**
     * name : givenValidUser_whenCreateUser_thenThrowNotYetImplementException
     * desc : 사용자 생성시 NotYetImplementedException 발생
     * 
     * @throws Exception
     */
    @Test
    public void givenValidUser_whenCreateUser_thenThrowNotYetImplementException() throws Exception {
        //given
        Users user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        String payload = new ObjectMapper().writeValueAsString(user);

        //when
        when(userController.createUser(any())).thenThrow(new NotYetImplementedException());
        MockHttpServletRequestBuilder request = post("/api/users/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload);

        //then
        mvc.perform(request)
            .andExpect(status().isNotImplemented())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotYetImplementedException));
    }
}