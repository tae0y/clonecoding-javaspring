package com.karrot.domain.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        //TODO: 필수값 검사후 예외 던지기

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
        throw new NotYetImplementedException();
    }

    @Override
    public UsersResponseDTO updateUser(Long id, UsersRequestDTO user)
    {
        throw new NotYetImplementedException();
    }

    @Override
    public void deleteUser(Long id)
    {
        throw new NotYetImplementedException();
    }
}
