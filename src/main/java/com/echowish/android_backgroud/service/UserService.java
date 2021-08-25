package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.User;

public interface UserService {

    String registerNewUsers(User user);

    //登录成功返回Integer值  失败返回-1（注意应该将android端未登录设为-1）
    Integer logOn(String account,String password);
}
