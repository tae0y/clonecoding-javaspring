package com.karrot.domain.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServiceImpl
 * - 사용자 정보 Service 레이어
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UsersResponseDTO createUser(UsersRequestDTO user)
    {
        // prepare
        UsersEntity entity = new UsersEntity(user);

        // process
        UsersEntity result = userRepository.save(entity);

        // return
        return new UsersResponseDTO(result);
    }

    @Override
    public List<UsersResponseDTO> getAllUsers()
    {
        // prepare
        // process
        List<UsersEntity> result = userRepository.findAll();
        List<UsersResponseDTO> responseList = new ArrayList<>();
        for (UsersEntity entity : result) {
            responseList.add(new UsersResponseDTO(entity));
        }

        // return
        return responseList;
    }

    @Override
    public UsersResponseDTO getUser(Long id)
    {
        // prepare
        // process
        UsersEntity result = userRepository.findById(id).orElse(null);

        // return
        return new UsersResponseDTO(result);
    }

    @Override
    public UsersResponseDTO updateUser(Long id, UsersRequestDTO user)
    {
        // prepare
        UsersEntity entity = new UsersEntity(user);
        entity.setId(id);

        // process
        UsersEntity result = userRepository.save(entity);

        // return
        return new UsersResponseDTO(result);
    }

    @Override
    public boolean deleteUser(Long id)
    {
        // prepare
        // process
        userRepository.deleteById(id);

        // return
        return true;
    }
}
