package com.huishuo.ghacsystem.service;

import com.huishuo.ghacsystem.model.User;

import java.util.List;

public interface UserService {
    void register(User user);

    void modifyUser(User user, User currentUser);

    List<User> listUser();

    void deleteUser(int userId);

    User matchUser(String userName, String userPwd);

    User findUser(int id);
}
