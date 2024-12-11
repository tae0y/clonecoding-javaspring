package com.karrot.domain.user;

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
    public Users createUser(Users user)
    {
        throw new NotYetImplementedException();
    }

    @Override
    public List<Users> getAllUsers()
    {
        throw new NotYetImplementedException();
    }

    @Override
    public Users getUser(Long id)
    {
        throw new NotYetImplementedException();
    }

    @Override
    public Users updateUser(Long id, Users user)
    {
        throw new NotYetImplementedException();
    }

    @Override
    public void deleteUser(Long id)
    {
        throw new NotYetImplementedException();
    }
}
