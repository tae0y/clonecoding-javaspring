package com.karrot.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    //TODO: JPA 이름규칙에 맞춰 수정하기
    /**
     * 사용자 정보를 저장한다(Create, Update)
     */
    public UsersEntity save(UsersEntity user);

    /**
     * 모든 사용자 정보를 조회한다(Read)
     */
    public List<UsersEntity> findAll();

    /**
     * 특정 사용자 정보를 조회한다(Read)
     */
    public Optional<UsersEntity> findById(Long id);

    //public Users updateById(Long id, Users user);

    /**
     * 특정 사용자 정보를 삭제한다(Delete)
     */
    public void deleteById(Long id);
}