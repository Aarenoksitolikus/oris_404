package dao;

import entity.UserEntity;
import util.HashUtil;

import java.sql.*;

/**
 * Рано или поздно этот класс должен быть преобразован в репозитории...
 */
public class DataClass {
    private static final String GET_USER_BY_CREDS = "select * from usr where username = ? and hash_password = ?";
    private static final String SAVE_NEW_USER = "insert into usr values (?, ?)";

    public void test(String username) {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement("select * from usr where username = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public void createUserTable() throws SQLException {
        getStatement().executeUpdate("""
                create table if not exists usr (
                id bigserial primary key,
                username varchar(255) not null,
                hash_password varchar(255) not null);""");
    }

    public UserEntity getUser(long id) throws SQLException {
        return convertFromResultSet(getStatement()
                .executeQuery("select * from usr where id = " + id + ";"));
    }

    public UserEntity getUser(String username, String hashPassword) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_USER_BY_CREDS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, HashUtil.hashPassword(hashPassword));
            return convertFromResultSet(preparedStatement.executeQuery());
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    public UserEntity saveNewUser(UserEntity entity) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SAVE_NEW_USER)) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getHashPassword());
            return convertFromResultSet(preparedStatement.executeQuery());
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public void updateUser(String username) throws SQLException {
        getStatement().executeUpdate("""
                update usr
                set username = '""" + username + "' where id = 1;");
    }

    public void deleteUser() throws SQLException {
        getStatement().executeUpdate("delete from usr where id = 1");
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        // это костыль, должно работать и без этого, НЕОБХОДИМО РАЗОБРАТЬСЯ!!!
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",
                "postgres", "Fadc766e!");
    }

    private UserEntity convertFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new UserEntity(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("hash_password")
            );
        }

        return null;
    }

    private Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("ваще всё поломалось, Наташа, мы ваще всё уронили");
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
