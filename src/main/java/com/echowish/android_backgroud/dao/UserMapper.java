package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.user.Friend;
import com.echowish.android_backgroud.pojo.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    void insertUser(User user);

    void deleteUserByAccount(String account);

    List<User> queryAllUser();

    User queryUserByAccount(String account);

    //查找账户数量 以便查看是否重复
    Integer queryAccountNumsByAccount(String account);

    //返回用户信息
    User queryUserByUserId(Integer userId);

    //拿到用户头像信息
    String queryImageByUserId(Integer userId);

    void updateImageByUserId(@Param(value = "userId") Integer userId, @Param("filename") String filename);

    void updateUserInfo(User user);

    //获取其他人信息 过滤掉账号 密码信息
    Friend queryFriendByUserId(Integer userId);
}
