package com.karrot.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {

    //TODO: JPA 이름규칙에 맞춰 수정하기
    public Users save(Users user);

    public List<Users> findAll();

    public Optional<Users> findById(Long id);

    //public Users updateById(Long id, Users user);

    public void deleteById(Long id);
}