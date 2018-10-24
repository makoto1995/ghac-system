package com.huishuo.ghacsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.huishuo.ghacsystem.dto.Result;
import com.huishuo.ghacsystem.exception.account.LoginException;
import com.huishuo.ghacsystem.exception.account.RegisterException;
import com.huishuo.ghacsystem.exception.account.UserControlException;
import com.huishuo.ghacsystem.model.User;
import com.huishuo.ghacsystem.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private Key key = MacProvider.generateKey();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<User> login(@RequestBody _loginInfo loginInfo, HttpSession httpSession) {
        try {
            if (this.userService.matchUser(loginInfo.username, loginInfo.password) != null) {
                User currentUser = this.userService.matchUser(loginInfo.username, loginInfo.password);
                httpSession.setAttribute("currentUser", currentUser);
                String token = Jwts.builder()
                        .setPayload(
                                JSON.toJSONString(new _id(String.valueOf(currentUser.getUserID()),
                                        String.valueOf(currentUser.getPrivileges()))))
                        .signWith(SignatureAlgorithm.HS512, key)
                        .compact();
                return new Result<>(true
                        , currentUser
                        , token);
            }
            throw new LoginException("用户名或密码错误，请重新输入！");
        } catch (LoginException e1) {
            return new Result<>(false, e1.getMessage());
        }
    }

    @PostMapping(value = "/", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<User> createUser(@RequestBody User user, HttpSession httpSession) {
        try {
            userService.register(user);
        } catch (RegisterException e1) {
            logger.error(e1.getMessage(), e1);
            return new Result<>(false, e1.getMessage());
        }
        User currentUser = this.userService.matchUser(user.getUserName(), user.getUserPwd());
        httpSession.setAttribute("currentUser", currentUser);
        return new Result<>(true,
                currentUser,
                Jwts.builder()
                        .setPayload(JSON.toJSONString(new _id(String.valueOf(currentUser.getUserID())
                                , String.valueOf(currentUser.getPrivileges()))))
                        .signWith(SignatureAlgorithm.HS512, key)
                        .compact());
    }

    @GetMapping(value = "/me", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<User> getCurrentUser(@RequestBody User currentUser) {
        try {
            return new Result<>(true, this.userService.matchUser(currentUser.getUserName(), currentUser.getUserPwd()));
        } catch (UserControlException e1) {
            logger.error("操作非法！", e1);
            return new Result<>(false, "操作非法！");
        }
    }

    @GetMapping(value = "/", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<List<User>> listUsers() {
        try {
            return new Result<>(true, userService.listUser());
        } catch (UserControlException e1) {
            logger.error(e1.getMessage(), e1);
            return new Result<>(false, e1.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<User> deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return new Result<>(true, new User());
        } catch (UserControlException e1) {
            logger.error(e1.getMessage(), e1);
            return new Result<>(false, e1.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<User> getUser(@PathVariable("id") int id) {
        try {
            return new Result<>(true, userService.findUser(id));
        } catch (UserControlException e1) {
            logger.error(e1.getMessage(), e1);
            return new Result<>(false, e1.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public Result<User> modifyUser(@RequestBody User user, HttpSession session){
        User currentUser = (User)session.getAttribute("currentUser");
        _privileges privileges = (_privileges) JSON.parse(currentUser.getPrivileges());
        try {
            if(currentUser.getUserID()==user.getUserID()|| privileges.canModifyUser){
                this.userService.modifyUser(user, currentUser);
                return new Result<>(true, userService.matchUser(user.getUserName(), user.getUserPwd()));
            } else {
                throw new UserControlException("无操作权限！");
            }
        } catch (UserControlException e1) {
            logger.error(e1.getMessage(), e1);
            return new Result<>(false, e1.getMessage());
        }
    }

    public final static class _loginInfo {
        @JSONField(name = "userName")
        public String username;
        @JSONField(name = "userPwd")
        public String password;
    }

    private final static class _id {
        public String id;
        public String privilege;

        public _id(String id, String privilege) {
            this.id = id;
            this.privilege = privilege;
        }
    }

    private final static class _privileges{
        @JSONField(name="canModifyUser")
        public boolean canModifyUser;
        @JSONField(name = "canModel")
        public boolean canModel;
        @JSONField(name = "canMonitor")
        public boolean canMonitor;
    }
}
