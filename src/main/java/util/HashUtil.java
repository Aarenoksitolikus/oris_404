package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Этот утилитный класс должен реализовывать
 * единственный метод -- хэширование пароля
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashUtil {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
