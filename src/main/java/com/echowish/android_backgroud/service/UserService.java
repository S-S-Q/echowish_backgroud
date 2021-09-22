package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.User;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    String registerNewUsers(User user);

    //登录成功返回Integer值  失败返回-1（注意应该将android端未登录设为-1）
    Integer logOn(String account,String password);

    User getUserMessage(int userId);

    ResponseEntity<FileSystemResource> downloadImage(Integer userId);

    //加载图片 不是接口
    String loadImage(MultipartFile file, int userId);

    //删除图片
    void deleteImage(String filename);

    //更新资料
    String updateUserInfo(User newuser);
}
