package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.ConcernMapper;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.Concern;
import com.echowish.android_backgroud.pojo.Friend;
import com.echowish.android_backgroud.pojo.MyConcern;
import com.echowish.android_backgroud.pojo.User;
import com.echowish.android_backgroud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    ConcernMapper concernMapper;

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
    public ResponseEntity<FileSystemResource> downloadImage(Integer userId) {
        String filename=userMapper.queryImageByUserId(userId);
        if(filename==null)
            return null;
        //通过路径 将该路径下的文件 转化为文件类
        File file= Paths.get(ReactInfo.LOAD_HEAD_IMAGE_PATH).resolve(filename).toFile();
        //文件存在且可读
        if(file.exists()&&file.canRead())
        {
            return ResponseEntity.ok()
                    .contentType(file.getName().contains("jpg")? MediaType.IMAGE_JPEG:MediaType.IMAGE_PNG)
                    .body(new FileSystemResource(file));
        }
        else
            return null;
    }

    @Override
    public String loadImage(MultipartFile file, int userId) {
        if(file==null||file.isEmpty())
            return ReactInfo.FAIL_INFO;
        try(InputStream inputStream=file.getInputStream())
        {
            Path uploadPath= Paths.get(ReactInfo.LOAD_HEAD_IMAGE_PATH);
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdir();
            String filename= UUID.randomUUID().toString().replace("-","");
            filename=filename+userId+".jpg";
            userMapper.updateImageByUserId(userId,filename);

            String deleteFilename=userMapper.queryImageByUserId(userId);
            if(!deleteFilename.equals(""))
            deleteImage(deleteFilename);
            Files.copy(inputStream, Paths.get(ReactInfo.LOAD_HEAD_IMAGE_PATH).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
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
            File Image=new File(String.valueOf(Paths.get(ReactInfo.LOAD_HEAD_IMAGE_PATH).resolve(filename)));
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
            Concern concern=concernMapper.queryIsConcern(userId,friId);
            friend=userMapper.queryFriendByUserId(userId);
            if (concern!=null)
                friend.isConcern=true;
            else
                friend.isConcern=false;
            return friend;
        }
        catch (Exception e)
        {
            return friend;
        }
    }


}
