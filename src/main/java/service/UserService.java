package service;

import entity.UserEntity;
import model.User;

public interface UserService {
    UserEntity authenticateUser(String username, String password);

    void changeUsername(User user, String newUsername);

    void save(User user);
}
