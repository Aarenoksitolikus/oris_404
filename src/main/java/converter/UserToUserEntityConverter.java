package converter;

import entity.UserEntity;
import model.User;
import util.HashUtil;

public class UserToUserEntityConverter implements Converter<User, UserEntity> {
    @Override
    public UserEntity convert(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .hashPassword(HashUtil.hashPassword(user.getPassword()))
                .build();
    }
}
