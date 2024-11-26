package com.karrot.domain.user;

import java.util.List;

public interface UserService {

    public User getEmployeeById(Long id);

    public User getEmployeeByName(String name);

    public List<User> getAllUsers();

    public boolean exists(String name);

    public User save(User employee);
}
