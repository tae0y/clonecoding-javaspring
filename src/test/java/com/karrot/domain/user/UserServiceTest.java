package com.karrot.domain.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * UserServiceTest
 * - 사용자 정보 Service 테스트
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UsersEntity testUser;
    private UsersRequestDTO testUserRequest;

    @BeforeEach
    void setUp() {
        testUserRequest = new UsersRequestDTO();
        // UsersRequestDTO 필드 설정

        testUser = new UsersEntity(testUserRequest);
        testUser.setId(1L);
    }

    /**
     * givenFullyValidUser_whenCreateUser_thenShouldReturnCreated
     * - 유효한 사용자 정보가 주어졌을때 사용자 생성이 잘 되는지
     */
    @Test
    void givenFullyValidUser_whenCreateUser_thenShouldReturnValueAssertNotNull() {
        // given
        when(userRepository.save(any(UsersEntity.class))).thenReturn(testUser);

        // when
        UsersResponseDTO result = userService.createUser(testUserRequest);

        // then
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(userRepository, times(1)).save(any(UsersEntity.class));
    }

    /**
     * givenInvalidUserName_whenCreateUser_thenThrowDataIntegrityViolationException
     * - 사용자 이름이 유효하지 않을때 DataIntegrityViolationException 예외 발생
     */
    @Test
    void givenInvalidUserName_whenCreateUser_thenThrowDataIntegrityViolationException() {
        // given
        testUserRequest.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        ConstraintViolationException constraintViolationException = new ConstraintViolationException(null, null, null);
        when(userRepository.save(any(UsersEntity.class)))
                .thenThrow(new DataIntegrityViolationException("Data integrity violation", constraintViolationException));

        // when & then
        assertThrows(DataIntegrityViolationException.class, () -> {
            userService.createUser(testUserRequest);
        });
    }

    /**
     * givenAnything_whenGetAllUsers_thenShouldReturnList
     * - 모든 사용자 정보를 가져올때 리스트로 반환
     */
    @Test
    void givenAnything_whenGetAllUsers_thenShouldReturnValueAssertNotNull() {
        // given
        List<UsersEntity> usersList = Arrays.asList(testUser);
        when(userRepository.findAll()).thenReturn(usersList);

        // when
        List<UsersResponseDTO> result = userService.getAllUsers();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    /**
     * givenExistingUserId_whenGetUser_thenShouldReturnUser
     * - 존재하는 사용자 ID로 사용자 정보를 가져올때 사용자 반환
     */
    @Test
    void givenExistingUserId_whenGetUser_thenShouldReturnUser() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // when
        UsersResponseDTO result = userService.getUser(1L);

        // then
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    /**
     * givenNonExistingUserId_whenGetUser_thenShouldThrowNullPointerException
     * - 존재하지 않는 사용자 ID로 사용자 정보를 가져올때 NullPointerException 예외 발생
     */
    @Test
    void givenNonExistingUserId_whenGetUser_thenShouldThrowNullPointerException() {
        // given
        when(userRepository.findById(999L)).thenThrow(new NullPointerException());

        // when & then
        assertThrows(NullPointerException.class, () -> {
            userService.getUser(999L);
        });
    }

    /**
     * givenExistingUserId_whenUpdateUser_thenShouldReturnUpdatedUser
     * - 존재하는 사용자 ID로 사용자 정보를 수정할때 수정된 사용자 반환
     */
    @Test
    void givenExistingUserId_whenUpdateUser_thenShouldReturnUpdatedUser() {
        // given
        when(userRepository.save(any(UsersEntity.class))).thenReturn(testUser);

        // when
        UsersResponseDTO result = userService.updateUser(1L, testUserRequest);

        // then
        assertNotNull(result);
        verify(userRepository, times(1)).save(testUser);
    }

    /**
     * givenNonExistingUserId_whenUpdateUser_thenShouldThrowNullPointerException
     * - 존재하지 않는 사용자 ID로 사용자 정보를 수정할때 NullPointerException 예외 발생
     */
    @Test
    void givenNonExistingUserId_whenUpdateUser_thenShouldThrowNullPointerException(){
        // given
        when(userRepository.findById(999L)).thenThrow(new NullPointerException());

        // when & then
        assertThrows(NullPointerException.class, () -> {
            userService.getUser(999L);
        });
    }

    /**
     * givenExistingUserId_whenDeleteUser_thenShouldThrowNothing
     * - 존재하는 사용자 ID로 사용자 정보를 삭제할때 성공 반환
     */
    @Test
    void givenExistingUserId_whenDeleteUser_thenShouldReturnSuccess() {
        // given
        doNothing().when(userRepository).deleteById(1L);

        // when
        userService.deleteUser(1L);

        // then
        verify(userRepository, times(1)).deleteById(1L);
    }

    /**
     * givenNonExistingUserId_whenDeleteUser_thenShouldThrowNullPointerException
     * - 존재하지 않는 사용자 ID로 사용자 정보를 삭제할때 NullPointerException 예외 발생
     */
    @Test
    void givenNonExistingUserId_whenDeleteUser_thenShouldThrowNullPointerException() {
        // given
        doThrow(new NullPointerException()).when(userRepository).deleteById(999L);

        // when & then
        assertThrows(NullPointerException.class, () -> {
            userService.deleteUser(999L);
        });
    }
} 