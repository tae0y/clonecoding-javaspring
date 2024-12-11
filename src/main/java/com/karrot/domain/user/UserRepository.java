package com.karrot.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {

    public Users createUser(Users user);

    public List<Users> getAllUsers();

    public Users getUser(Long id);

    public Users updateUser(Long id, Users user);

    public void deleteUser(Long id);
}