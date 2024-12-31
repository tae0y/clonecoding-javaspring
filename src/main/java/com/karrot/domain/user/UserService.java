package com.karrot.domain.user;

import java.util.List;

public interface UserService {

    public UsersResponseDTO createUser(UsersRequestDTO user);

    public List<UsersResponseDTO> getAllUsers();

    public UsersResponseDTO getUser(Long id);

    public UsersResponseDTO updateUser(Long id, UsersRequestDTO user);

    public boolean deleteUser(Long id);
}
