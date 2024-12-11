package com.karrot.domain.user;

import java.util.List;

public interface UserService {

    public Users createUser(Users user);

    public List<Users> getAllUsers();

    public Users getUser(Long id);

    public Users updateUser(Long id, Users user);

    public void deleteUser(Long id);
}
