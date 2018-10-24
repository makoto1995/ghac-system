package com.huishuo.ghacsystem.service.impl;

import com.huishuo.ghacsystem.exception.account.RegisterException;
import com.huishuo.ghacsystem.exception.account.UserControlException;
import com.huishuo.ghacsystem.mapper.staticdata.UserMapper;
import com.huishuo.ghacsystem.model.User;
import com.huishuo.ghacsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(User user) {
        try {
            if (this.userMapper.findUserByName(user.getUserName()) != null) {
                throw new RegisterException("用户名重复！");
            } else {
                this.userMapper.addUser(user.getUserName(), user.getUserPwd(), user.getPrivileges());
            }
        } catch (RegisterException e) {
            throw e;
        } catch (Exception e) {
            throw new RegisterException("SQL内部错误！" + e.getMessage());
        }
    }

    @Override
    public void deleteUser(int userId) {
        this.userMapper.deleteUser(userId);
    }

    @Override
    public void modifyUser(User user, User currentUser) {
        try{
            if (this.userMapper.matchUser(currentUser) != null) {
                this.userMapper.modifyUser(user.getUserID(), user.getUserName(), user.getUserPwd(), user.getPrivileges());
            } else {
                throw new UserControlException("非法操作！");
            }
        }catch (UserControlException e1) {
            throw e1;
        } catch (Exception e) {
            throw new UserControlException("发生内部错误！" + e.getMessage());
        }
    }

    @Override
    public List<User> listUser() {
        return this.userMapper.listUser();
    }

    @Override
    public User matchUser(String userName, String userPwd) {
        return this.userMapper.matchUser(new User(0, userName, userPwd, null));
    }

    @Override
    public User findUser(int id) {
        return this.userMapper.findUserById(id);
    }
}
