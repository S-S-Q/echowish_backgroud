package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.User;
import com.echowish.android_backgroud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String registerNewUsers(User user) {
        //出现重复账户 注册失败
        if(userMapper.queryAccountNumsByAccount(user.getAccount())!=0)
            return ReactInfo.REGISTER_FAIL;
        //尝试注册
        try {
            userMapper.insertUser(user);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public Integer logOn(String account, String password) {
        try
        {
            User user=userMapper.queryUserByAccount(account);
            if(user.getPassword().equals(password))
            {
                return user.getUserId();
            }
            else
                return -1;
        }
        catch (Exception e)
        {
            return -1;
        }

    }

    @Override
    public User getUserMessage(int userId) {
        User user=null;
        try {
            user=userMapper.queryUserByUserId(userId);
            return user;
        }
        catch (Exception e)
        {
            return user;
        }
    }
}
