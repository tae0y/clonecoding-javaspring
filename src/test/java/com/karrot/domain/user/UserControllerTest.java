package com.karrot.domain.user;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.cfg.NotYetImplementedException;

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

    /**
     * name : givenValidUser_whenGetAllUsers_thenThrowNotYetImplementException
     * desc : 모든 사용자 조회시 NotYetImplementedException 발생
     * 
     * @throws Exception
     */
    @Test
    public void givenValidUser_whenGetAllUsers_thenThrowNotYetImplementException() throws Exception {
        //when
        when(userController.getAllUsers()).thenThrow(new NotYetImplementedException());
        MockHttpServletRequestBuilder request = get("/api/users/")
            .contentType(MediaType.APPLICATION_JSON);

        //then
        mvc.perform(request)
            .andExpect(status().isNotImplemented())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotYetImplementedException));
    }

    /**
     * name : givenValidUserId_whenGetUser_thenThrowNotYetImplementException
     * desc : 사용자 ID로 조회시 NotYetImplementedException 발생
     * 
     * @throws Exception
     */
    @Test
    public void givenValidUserId_whenGetUser_thenThrowNotYetImplementException() throws Exception {
        //when
        when(userController.getUser(any(Long.class))).thenThrow(new NotYetImplementedException());
        MockHttpServletRequestBuilder request = get("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON);

        //then
        mvc.perform(request)
            .andExpect(status().isNotImplemented())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotYetImplementedException));
    }

    /**
     * name : givenValidUserIdAndUser_whenUpdateUser_thenThrowNotYetImplementException
     * desc : 사용자 ID로 업데이트시 NotYetImplementedException 발생
     * 
     * @throws Exception
     */
    @Test
    public void givenValidUserIdAndUser_whenUpdateUser_thenThrowNotYetImplementException() throws Exception {
        //given
        Users user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        String payload = new ObjectMapper().writeValueAsString(user);

        //when
        when(userController.updateUser(any(Long.class), any(Users.class))).thenThrow(new NotYetImplementedException());
        MockHttpServletRequestBuilder request = put("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload);

        //then
        mvc.perform(request)
            .andExpect(status().isNotImplemented())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotYetImplementedException));
    }

    /**
     * name : givenValidUserId_whenDeleteUser_thenThrowNotYetImplementException
     * desc : 사용자 ID로 삭제시 NotYetImplementedException 발생
     * 
     * @throws Exception
     */
    @Test
    public void givenValidUserId_whenDeleteUser_thenThrowNotYetImplementException() throws Exception {
        //when
        when(userController.deleteUser(any(Long.class))).thenThrow(new NotYetImplementedException());
        MockHttpServletRequestBuilder request = delete("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON);

        //then
        mvc.perform(request)
            .andExpect(status().isNotImplemented())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotYetImplementedException));
    }


}