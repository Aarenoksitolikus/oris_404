package service;

import entity.UserEntity;

public interface UserService {
    UserEntity authenticateUser(String username, String password);
}
