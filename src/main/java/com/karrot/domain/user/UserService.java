package com.karrot.domain.user;

import java.util.List;

public interface UserService {

    public Users getEmployeeById(Long id);

    public Users getEmployeeByName(String name);

    public List<Users> getAllUsers();

    public boolean exists(String name);

    public Users save(Users employee);
}
