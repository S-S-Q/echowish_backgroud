package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.user.Friend;
import com.echowish.android_backgroud.pojo.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    String registerNewUsers(User user);

    String deleteUser(String account);

    List<User> queryAllUser();

    //登录成功返回Integer值  失败返回-1（注意应该将android端未登录设为-1）
    Integer logOn(String account,String password);

    User getUserMessage(int userId);


    //加载图片 不是接口
    String loadImage(MultipartFile file, int userId);

    //删除图片
    void deleteImage(String filename);

    //更新资料
    String updateUserInfo(User newuser);

    //获取朋友信息
    Friend getFriendMessage(Integer userId,Integer friId);


}
