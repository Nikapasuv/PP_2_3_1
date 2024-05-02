package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    User findUser(int id);

    void updateUser(User user);

    void deleteUser(int id);

    List<User> getAllUsers();
}
