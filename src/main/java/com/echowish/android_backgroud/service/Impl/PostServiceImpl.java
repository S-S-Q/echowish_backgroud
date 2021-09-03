package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.pojo.Post;
import com.echowish.android_backgroud.service.PostService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    //添加新的帖子 并且判断帖子是否符合条件
    @Override
    public String publishPost(Post post) {
        try{
            if(post.reward.equals(""))
                post.reward=null;
            postMapper.insertPost(post);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e) {
            System.out.println(e);
           return ReactInfo.FAIL_INFO;
        }
    }



    @Override
    public String loadImage(MultipartFile file, String filename) {
        if(file==null||file.isEmpty()||filename==null||filename.isEmpty())
            return ReactInfo.FAIL_INFO;
        try(InputStream inputStream=file.getInputStream())
        {
            Path uploadPath= Paths.get(ReactInfo.LOAD_IMAGE_PATH);
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdir();

            Files.copy(inputStream, Paths.get(ReactInfo.LOAD_IMAGE_PATH).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            System.out.println(ReactInfo.FAIL_INFO);
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String loadImage(MultipartFile file, String filename, Integer postId) {
        if(file==null||file.isEmpty()||filename==null||filename.isEmpty())
            return ReactInfo.FAIL_INFO;
        try(InputStream inputStream=file.getInputStream())
        {
            Path uploadPath= Paths.get(ReactInfo.LOAD_IMAGE_PATH);
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdir();

            Files.copy(inputStream, Paths.get(ReactInfo.LOAD_IMAGE_PATH).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            postMapper.updateImageByPostId(postId,filename);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            //如果失败删掉保存的图片
            try {
                File saveFile=Paths.get(ReactInfo.LOAD_IMAGE_PATH).resolve(filename).toFile();
                if(saveFile.exists()&&saveFile.canRead())
                    saveFile.delete();
            }
            catch (Exception ee)
            {

            }
            return ReactInfo.FAIL_INFO;
        }
    }

    //通过图片路径查找图片 测试用 记得删除
    @Override
    public ResponseEntity<FileSystemResource> downloadImage(String filename) {
        if(filename==null)
            return null;
        //通过路径 将该路径下的文件 转化为文件类
        File file=Paths.get(ReactInfo.LOAD_IMAGE_PATH).resolve(filename).toFile();
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

    //通过帖子id获取图片
    @Override
    public ResponseEntity<FileSystemResource> downloadImage(Integer postId) {
        String filename=postMapper.queryImageByPostId(postId);
        if(filename==null)
            return null;
        //通过路径 将该路径下的文件 转化为文件类
        File file=Paths.get(ReactInfo.LOAD_IMAGE_PATH).resolve(filename).toFile();
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
}
