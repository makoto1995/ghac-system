package com.huishuo.ghacsystem.mapper.staticdata;

import com.huishuo.ghacsystem.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    @Insert("INSERT INTO TBL_STATIC_USER(user_name, user_password, user_privilege) VALUES(#{userName}, #{userPwd}, null) ")
    void addUser(@Param("userName") String userName
            , @Param("userPwd") String userPwd
            , @Param("privilegeJSONString") String privilegeJSONString);

    @Select("SELECT * FROM TBL_STATIC_USER")
    @Results({
            @Result(property = "userID", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_password"),
            @Result(property = "privileges", column = "user_privilege")
    })
    List<User> listUser();

    @Update("UPDATE TBL_STATIC_USER SET user_name = #{newUserName}, user_password = #{newUserPwd}, user_privilege = #{newPrivilegeJSONString} WHERE user_id = #{userID}")
    void modifyUser(@Param("userID") int userID
            , @Param("newUserName") String newUserName
            , @Param("newUserPwd") String newUserPwd
            , @Param("newPrivilegeJSONString") String newPrivilegeJSONString);

    @Delete("DELETE FROM TBL_STATIC_USER WHERE user_id = #{userID}")
    void deleteUser(@Param("userID") int userID);

    @Select("SELECT * FROM TBL_STATIC_USER WHERE user_name = #{user.userName} and user_password = #{user.userPwd}")
    @Results({
            @Result(property = "userID", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_password"),
            @Result(property = "privileges", column = "user_privilege")
    })
    User matchUser(@Param("user") User user);

    @Select("SELECT * FROM TBL_STATIC_USER WHERE user_name = #{userName}")
    @Results({
            @Result(property = "userID", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_password"),
            @Result(property = "privileges", column = "user_privilege")
    })
    User findUserByName(@Param("userName") String userName);

    @Select("SELECT * FROM TBL_STATIC_USER WHERE user_id = #{id}")
    @Results({
            @Result(property = "userID", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_password"),
            @Result(property = "privileges", column = "user_privilege")
    })
    User findUserById(@Param("id")int id);
}
