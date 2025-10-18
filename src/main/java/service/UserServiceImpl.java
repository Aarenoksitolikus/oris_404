package service;

import dao.DataClass;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final DataClass dataClass;

    public UserEntity authenticateUser(String username, String password) {
        return dataClass.getUser(username, password);
    }
}
