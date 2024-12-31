package com.karrot.domain.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * UserControllerTest
 * - 사용자 정보 Controller 테스트
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsersRequestDTO testUserRequest;
    private UsersResponseDTO testUserResponse;

    @BeforeEach
    void setUp() {
        testUserRequest = new UsersRequestDTO();
        // UsersRequestDTO 필드 설정

        testUserResponse = new UsersResponseDTO();
        testUserResponse.setId(1L);
        // UsersResponseDTO 필드 설정
    }

    /**
     * givenFullyValidUser_whenCreateUser_thenShouldReturnCreated
     * - 유효한 사용자 정보가 주어졌을때 사용자 생성이 잘 되는지
     * @throws Exception
     */
    @Test
    void givenFullyValidUser_whenCreateUser_thenShouldReturnCreated() throws Exception {
        // given
        // testUserResponse
        when(userService.createUser(any(UsersRequestDTO.class))).thenReturn(testUserResponse);

        // when & then
        mockMvc.perform(post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data[0].id").value(1L));
    }

    /**
     * givenInvalidUserName_whenCreateUser_thenShouldReturnBadRequest
     * - 사용자 이름이 유효하지 않을때 InvalidInput 반환
     * @throws Exception
     */
    @Test
    void givenInvalidUserName_whenCreateUser_thenShouldReturnBadRequest() throws Exception {
        // given
        testUserRequest.setName("123456789101112131415");

        // TODO: 중첩된 예외케이스의 단위테스트는 어떻게 작성해야 하는가
        ConstraintViolationException constraintViolationException = new ConstraintViolationException(null, null, null);
        when(userService.createUser(any(UsersRequestDTO.class)))
                .thenThrow(new DataIntegrityViolationException("Data integrity violation", constraintViolationException));

        // when & then
        mockMvc.perform(post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserRequest)))
                .andExpect(status().isBadRequest());
    }

    /**
     * givenAnything_whenGetAllUsers_thenShouldReturnList
     * - 사용자 전체 조회시 사용자 리스트 반환
     * @throws Exception
     */
    @Test
    void givenAnything_whenGetAllUsers_thenShouldReturnList() throws Exception {
        // given
        List<UsersResponseDTO> usersList = Arrays.asList(testUserResponse);
        when(userService.getAllUsers()).thenReturn(usersList);

        // when & then
        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L));
    }

    /**
     * givenExistingUserId_whenGetUser_thenShouldReturnUser
     * - 존재하는 사용자 ID로 사용자 조회시 사용자 반환
     * @throws Exception
     */
    @Test
    void givenExistingUserId_whenGetUser_thenShouldReturnUser() throws Exception {
        // given
        when(userService.getUser(1L)).thenReturn(testUserResponse);

        // when & then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L));
    }

    /**
     * givenNonExistingUserId_whenGetUser_thenShouldReturnNotFound
     * - 존재하지 않는 사용자 ID로 사용자 조회시 NotFound 반환
     * @throws Exception
     */
    @Test
    void givenNonExistingUserId_whenGetUser_thenShouldReturnNotFound() throws Exception {
        // given
        when(userService.getUser(999L)).thenReturn(null);

        // when & then
        mockMvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound());
    }

    /**
     * givenExistingUserId_whenUpdateUser_thenShouldReturnUpdatedUser
     * - 존재하는 사용자 ID로 사용자 수정시 수정된 사용자 반환
     * @throws Exception
     */
    @Test
    void givenExistingUserId_whenUpdateUser_thenShouldReturnUpdatedUser() throws Exception {
        // given
        when(userService.updateUser(eq(1L), any(UsersRequestDTO.class))).thenReturn(testUserResponse);

        // when & then
        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L));
    }

    /**
     * givenNonExistingUserId_whenUpdateUser_thenShouldReturnNotFound
     * - 존재하지 않는 사용자 ID로 사용자 수정시 NotFound 반환
     * @throws Exception
     */
    @Test
    void givenNonExistingUserId_whenUpdateUser_thenShouldReturnNotFound() throws Exception {
        // given
        when(userService.updateUser(eq(999L), any(UsersRequestDTO.class))).thenThrow(new NullPointerException());

        // when & then
        mockMvc.perform(put("/api/users/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserRequest)))
                .andExpect(status().isNotFound());
    }

    /**
     * givenExistingUserId_whenDeleteUser_thenShouldReturnSuccess
     * - 존재하는 사용자 ID로 사용자 삭제시 성공 반환
     * @throws Exception
     */
    @Test
    void givenExistingUserId_whenDeleteUser_thenShouldReturnSuccess() throws Exception {
        when(userService.deleteUser(eq(1L))).thenReturn(true);
        
        // when & then
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }

    /**
     * givenNonExistingUserId_whenDeleteUser_thenShouldReturnNotFound
     * - 존재하지 않는 사용자 ID로 사용자 삭제시 NotFound 반환
     * @throws Exception
     */
    @Test
    void givenNonExistingUserId_whenDeleteUser_thenShouldReturnNotFound() throws Exception {
        when(userService.deleteUser(eq(999L))).thenThrow(new NullPointerException());

        // when & then
        mockMvc.perform(delete("/api/users/999"))
                .andExpect(status().isNotFound());
    }
}