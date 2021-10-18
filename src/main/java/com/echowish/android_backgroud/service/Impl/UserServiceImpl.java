package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.bean.ServerPathPropBean;
import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.Friend;
import com.echowish.android_backgroud.pojo.User;
import com.echowish.android_backgroud.service.ConcernService;
import com.echowish.android_backgroud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ConcernService concernService;
    @Autowired
    ServerPathPropBean serverPathPropBean;

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

    @Override
    public String loadImage(MultipartFile file, int userId) {
        if(file==null||file.isEmpty())
            return ReactInfo.FAIL_INFO;
        try(InputStream inputStream=file.getInputStream())
        {
            Path uploadPath= Paths.get(serverPathPropBean.getHeadImagePath());
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdir();
            String filename= UUID.randomUUID().toString().replace("-","");
            filename=filename+userId+".jpg";

            String deleteFilename=userMapper.queryImageByUserId(userId);
            userMapper.updateImageByUserId(userId,filename);

            if(!deleteFilename.equals(""))
            deleteImage(deleteFilename);
            Files.copy(inputStream, Paths.get(serverPathPropBean.getHeadImagePath()).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            System.out.println(ReactInfo.FAIL_INFO);
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public void deleteImage(String filename) {
        if(filename==null||filename.isEmpty())
            return;
        try{
            File Image=new File(String.valueOf(Paths.get(serverPathPropBean.getHeadImagePath()).resolve(filename)));
            if(!Image.exists())
                return;
            else
                Image.delete();
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public String updateUserInfo(User newuser) {
        try {
            userMapper.updateUserInfo(newuser);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public Friend getFriendMessage(Integer userId,Integer friId) {
        Friend friend=null;
        try
        {
            friend=userMapper.queryFriendByUserId(userId);
            friend.isConcern=concernService.queryIsConcern(userId,friId);
            return friend;
        }
        catch (Exception e)
        {
            return friend;
        }
    }
}
