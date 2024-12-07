package com.karrot.domain.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerOpenApiTest {
    //스프링 전체 컨텍스트 로드가 필요해서 단위테스트를 분리함

    @Autowired
    private TestRestTemplate restTemplate;

    //TODO: OpenAPI 스펙에 대한 단위테스트
    /**
     * name : givenSwaggerDocs_whenCreateUser_thenShouldIncludeEssentialFields
     * desc : CreateUser OpenAPI 문서에 필수 필드가 포함되어야 함
     *        - summary, description, responseCode, responseMessage, responseSchema
     */
    @Test
    public void givenSwaggerDocs_whenCreateUser_thenShouldIncludeEssentialFields() throws Exception {
        //given
        ResponseEntity<String> response = restTemplate.getForEntity("/v3/api-docs", String.class);
        assertEquals(200, response.getStatusCodeValue());

        //when and then
        JsonNode swagger = new ObjectMapper().readTree(response.getBody());
        JsonNode createUserRoot = swagger.get("paths").get("/api/users/").get("post");
        assertNotNull(createUserRoot.get("tags"));
        assertNotNull(createUserRoot.get("summary"));
        assertNotNull(createUserRoot.get("description"));
        assertNotNull(createUserRoot.get("operationId"));

        JsonNode createUserRequestBody = createUserRoot.get("requestBody");
        assertNotNull(createUserRequestBody.get("content"));

        JsonNode createUserResponses = createUserRoot.get("responses");
        assertNotNull(createUserResponses.get("201"));
        assertNotNull(createUserResponses.get("400"));
        assertNotNull(createUserResponses.get("403"));
        assertNotNull(createUserResponses.get("409"));
        assertNotNull(createUserResponses.get("503"));
    }
}
