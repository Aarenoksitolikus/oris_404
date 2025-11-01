package service;

import converter.UserToUserEntityConverter;
import dao.DataClass;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import model.User;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final DataClass dataClass;
    private final UserToUserEntityConverter converter;

    public UserEntity authenticateUser(String username, String password) {
        return dataClass.getUser(username, password);
    }

    @Override
    public void changeUsername(User user, String newUsername) {
        user.setUsername(newUsername);
    }

    @Override
    public void save(User user) {
        dataClass.saveNewUser(converter.convert(user));
    }
}
