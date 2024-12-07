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
    public Users getEmployeeById(Long id) {
        throw new NotYetImplementedException();
    }

    @Override
    public Users getEmployeeByName(String name) {
        throw new NotYetImplementedException();
    }

    @Override
    public boolean exists(String name) {
        throw new NotYetImplementedException();
    }

    @Override
    public Users save(Users user) {
        throw new NotYetImplementedException();
    }

    @Override
    public List<Users> getAllUsers() {
        throw new NotYetImplementedException();
    }
}
