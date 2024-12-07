package com.karrot.domain.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
     * desc : createUser OpenAPI 문서에 필수 필드가 포함되어야 함
     * 
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

        //TODO: [개선] 메서드별 필수 파라미터 확인, requestBody였는데.. 지금은 parameters가 나옴, 불과 몇시간만에(??)
        //JsonNode createUserRequestBody = createUserRoot.get("requestBody");
        //assertNotNull(createUserRequestBody.get("content"));

        JsonNode createUserResponses = createUserRoot.get("responses");
        assertNotNull(createUserResponses.get("201"));
        assertNotNull(createUserResponses.get("400"));
        assertNotNull(createUserResponses.get("403"));
        assertNotNull(createUserResponses.get("409"));
        assertNotNull(createUserResponses.get("503"));
    }

    /**
     * name : givenSwaggerDocs_whenGetAllUsers_thenShouldIncludeEssentialFields
     * desc : getAllUsers OpenAPI 문서에 필수 필드가 포함되어야 함
     * 
     */
    @Test
    public void givenSwaggerDocs_whenGetAllUsers_thenShouldIncludeEssentialFields() throws Exception {
        //given
        ResponseEntity<String> response = restTemplate.getForEntity("/v3/api-docs", String.class);
        assertEquals(200, response.getStatusCodeValue());

        //when and then
        JsonNode swagger = new ObjectMapper().readTree(response.getBody());
        JsonNode createUserRoot = swagger.get("paths").get("/api/users/").get("get");
        assertNotNull(createUserRoot.get("tags"));
        assertNotNull(createUserRoot.get("summary"));
        assertNotNull(createUserRoot.get("description"));
        assertNotNull(createUserRoot.get("operationId"));

        //JsonNode createUserRequestBody = createUserRoot.get("requestBody");
        //assertNotNull(createUserRequestBody.get("content"));

        JsonNode createUserResponses = createUserRoot.get("responses");
        assertNotNull(createUserResponses.get("200"));
        assertNotNull(createUserResponses.get("403"));
        assertNotNull(createUserResponses.get("503"));
    }

    /**
     * name : givenSwaggerDocs_whenUpdateUser_thenShouldIncludeEssentialFields
     * desc : updateUser OpenAPI 문서에 필수 필드가 포함되어야 함
     * 
     */
    @Test
    public void givenSwaggerDocs_whenUpdateUser_thenShouldIncludeEssentialFields() throws Exception {
        //given
        ResponseEntity<String> response = restTemplate.getForEntity("/v3/api-docs", String.class);
        assertEquals(200, response.getStatusCodeValue());

        //when and then
        JsonNode swagger = new ObjectMapper().readTree(response.getBody());
        JsonNode updateUserRoot = swagger.get("paths").get("/api/users/{id}").get("put");
        assertNotNull(updateUserRoot.get("tags"));
        assertNotNull(updateUserRoot.get("summary"));
        assertNotNull(updateUserRoot.get("description"));
        assertNotNull(updateUserRoot.get("operationId"));

        //JsonNode updateUserRequestBody = updateUserRoot.get("requestBody");
        //assertNotNull(updateUserRequestBody.get("content"));

        JsonNode updateUserResponses = updateUserRoot.get("responses");
        assertNotNull(updateUserResponses.get("200"));
        assertNotNull(updateUserResponses.get("400"));
        assertNotNull(updateUserResponses.get("403"));
        assertNotNull(updateUserResponses.get("404"));
        assertNotNull(updateUserResponses.get("503"));
    }

    /**
     * name : givenSwaggerDocs_whenDeleteUser_thenShouldIncludeEssentialFields
     * desc : deleteUser OpenAPI 문서에 필수 필드가 포함되어야 함
     * 
     */
    @Test
    public void givenSwaggerDocs_whenDeleteUser_thenShouldIncludeEssentialFields() throws Exception {
        //given
        ResponseEntity<String> response = restTemplate.getForEntity("/v3/api-docs", String.class);
        assertEquals(200, response.getStatusCodeValue());

        //when and then
        JsonNode swagger = new ObjectMapper().readTree(response.getBody());
        JsonNode deleteUserRoot = swagger.get("paths").get("/api/users/{id}").get("delete");
        assertNotNull(deleteUserRoot.get("tags"));
        assertNotNull(deleteUserRoot.get("summary"));
        assertNotNull(deleteUserRoot.get("description"));
        assertNotNull(deleteUserRoot.get("operationId"));

        JsonNode deleteUserResponses = deleteUserRoot.get("responses");
        assertNotNull(deleteUserResponses.get("200"));
        assertNotNull(deleteUserResponses.get("403"));
        assertNotNull(deleteUserResponses.get("404"));
        assertNotNull(deleteUserResponses.get("503"));
    }
}
