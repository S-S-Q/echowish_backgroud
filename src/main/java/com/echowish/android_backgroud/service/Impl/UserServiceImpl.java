package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.bean.ServerPathPropBean;
import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.user.Friend;
import com.echowish.android_backgroud.pojo.user.User;
import com.echowish.android_backgroud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ConcernService concernService;
    @Autowired
    PreConcernService preConcernService;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    ServerPathPropBean serverPathPropBean;


    @Override
    public String registerNewUsers(User user) {
        //出现重复账户 注册失败[这里会在高并发时 出现错误]
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
    public String deleteUser(String account) {
        try {
            User user=userMapper.queryUserByAccount(account);
            String ans1=postService.deletePostByUserId(user.getUserId());

            String ans2=concernService.deleteFriendByUserId(user.getUserId());
            String ans3=preConcernService.deleteConcernRequestByUserId(user.getUserId());
            String ans4=commentService.deleteCommentByUserId(user.getUserId());
            userMapper.deleteUserByAccount(account);

            if(ans1==ReactInfo.SUCCESS_INFO
            &&ans2==ReactInfo.SUCCESS_INFO
            &&ans3==ReactInfo.SUCCESS_INFO
            &&ans4==ReactInfo.SUCCESS_INFO)
            return ReactInfo.SUCCESS_INFO;

            return ReactInfo.FAIL_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public List<User> queryAllUser() {
        try
        {
            return userMapper.queryAllUser();
        }
        catch (Exception e)
        {
            return null;
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
            friend=userMapper.queryFriendByUserId(friId);
            friend.isConcern=concernService.queryIsConcern(userId,friId);
            return friend;
        }
        catch (Exception e)
        {
            return friend;
        }
    }
}
