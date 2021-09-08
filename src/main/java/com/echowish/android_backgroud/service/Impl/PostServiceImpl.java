package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.pojo.PartPost;
import com.echowish.android_backgroud.pojo.Post;
import com.echowish.android_backgroud.service.PostService;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    //添加新的帖子 并且判断帖子是否符合条件
    @Override
    public String publishPost(MultipartFile file,Post post) {
        try{
            if(post.reward.equals(""))
                post.reward=null;

            if(loadImage(file,post.postImage))
            {
                postMapper.insertPost(post);

                return ReactInfo.SUCCESS_INFO;
            }
            else
            {
                deleteImage(post.postImage);
                return ReactInfo.FAIL_INFO;
            }

        }
        catch (Exception e) {
            System.out.println(e);
           return ReactInfo.FAIL_INFO;
        }
    }



    @Override
    public boolean loadImage(MultipartFile file, String filename) {
        if(file==null||file.isEmpty()||filename==null||filename.isEmpty())
            return true;
        try(InputStream inputStream=file.getInputStream())
        {
            Path uploadPath= Paths.get(ReactInfo.LOAD_IMAGE_PATH);
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdir();

            Files.copy(inputStream, Paths.get(ReactInfo.LOAD_IMAGE_PATH).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(ReactInfo.FAIL_INFO);
            return false;
        }
    }

    @Override
    public void deleteImage(String filename) {
        if(filename==null||filename.isEmpty())
            return;
        try{
            File Image=new File(String.valueOf(Paths.get(ReactInfo.LOAD_IMAGE_PATH).resolve(filename)));
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
    public List<Post> queryPost(int start, int end) {
        List returnList=null;
        try
        {
            List list=postMapper.queryAllPost();
            //如果开头就大于 list的大小 那么就返回空
            if(start>list.size())
                return null;
            //如果尾部大于长度 则 返回 最大长度
            end=end>list.size()?returnList.size():end;
            returnList=list.subList(start,end);
            return returnList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<PartPost> queryPartPost(int start, int end) {
        List returnList=null;
        try
        {
            List list=postMapper.queryAllPartPost();
            //如果开头就大于 list的大小 那么就返回空
            if(start>list.size())
                return null;
            //如果尾部大于长度 则 返回 最大长度
            end=end>list.size()?returnList.size():end;
            returnList=list.subList(start,end);
            return returnList;
        }
        catch (Exception e)
        {
            return null;
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
