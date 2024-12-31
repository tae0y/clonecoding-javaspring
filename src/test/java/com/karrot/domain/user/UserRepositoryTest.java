package com.karrot.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UsersEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UsersEntity();
        testUser.setName("Test User");
        // ...필드 설정...
        userRepository.save(testUser);
    }

    @Test
    void givenValidUser_whenSave_thenShouldReturnSavedUser() {
        // given
        UsersEntity newUser = new UsersEntity();
        newUser.setName("New User");

        // when
        UsersEntity savedUser = userRepository.save(newUser);

        // then
        assertNotNull(savedUser);
        assertEquals(newUser.getName(), savedUser.getName());
    }

    @Test
    //@Transactional
    void givenInvalidUser_whenSave_thenShouldThrowException() {
        // given
        UsersEntity invalidUser = new UsersEntity();
        invalidUser.setName(null);
        invalidUser.setIntroduction("Hello! I'm Invalid User");

        // when & then
        assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(invalidUser);
            userRepository.flush();
        });
    }

    @Test
    void givenExistingUserId_whenFindById_thenShouldReturnUser() {
        // when
        Optional<UsersEntity> foundUser = userRepository.findById(testUser.getId());

        // then
        assertTrue(foundUser.isPresent());
        assertEquals(testUser.getName(), foundUser.get().getName());
    }

    @Test
    void givenNonExistingUserId_whenFindById_thenShouldReturnEmpty() {
        // when
        Optional<UsersEntity> foundUser = userRepository.findById(999L);

        // then
        assertFalse(foundUser.isPresent());
    }

    @Test
    void givenExistingUser_whenDelete_thenShouldDeleteUser() {
        // when
        userRepository.delete(testUser);

        // then
        Optional<UsersEntity> foundUser = userRepository.findById(testUser.getId());
        assertFalse(foundUser.isPresent());
    }

    @Test
    void givenNonExistingUser_whenDelete_thenShouldThrowException() {
        // given
        Long nonExistingId = 999L; // 데이터베이스에 존재하지 않는 ID

        // when & then
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userRepository.deleteById(nonExistingId);
            userRepository.flush();
        });
    }
}
