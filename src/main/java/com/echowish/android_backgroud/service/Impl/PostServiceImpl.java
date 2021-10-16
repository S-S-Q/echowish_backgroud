package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.bean.ServerPathPropBean;
import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.CommentMapper;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.pojo.DetailPost;
import com.echowish.android_backgroud.pojo.MyPublishPost;
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
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ServerPathPropBean serverPathPropBean;

    //添加新的帖子 并且判断帖子是否符合条件
    @Override
    public String publishPost(MultipartFile file,Post post) {
        try{
            if(post.postImage==null)
                post.postImage="";


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
    public String deletePost(int postId) {
        try {
            commentMapper.deleteCommentByPostId(postId);
            postMapper.deleteByPostId(postId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }


    @Override
    public boolean loadImage(MultipartFile file, String filename) {
        if(file==null||file.isEmpty()||filename==null||filename.isEmpty())
            return true;
        try(InputStream inputStream=file.getInputStream())
        {
            Path uploadPath= Paths.get(serverPathPropBean.getImagePath());
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdir();

            Files.copy(inputStream, Paths.get(serverPathPropBean.getImagePath()).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
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
            File Image=new File(String.valueOf(Paths.get(serverPathPropBean.getImagePath()).resolve(filename)));
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
    public Post queryPost(Integer postId) {
        Post post=null;
        try
        {
            if(postId==null)
                return null;
            post=postMapper.queryPost(postId);
            //如果开头就大于 list的大小 那么就返回空
            //如果尾部大于长度 则 返回 最大长度
            return post;
        }
        catch (Exception e)
        {
            return post;
        }
    }

    @Override
    public DetailPost queryDetailPost(Integer postId) {
        DetailPost detailPost=null;
        try
        {
            detailPost=postMapper.queryDetailPost(postId);
            System.out.println(detailPost);
            return detailPost;
        }
        catch (Exception e)
        {
            System.out.println("失败");
            return detailPost;
        }
    }

    @Override
    public List<PartPost> queryPartPost(int start, int end) {
        System.out.println(start);
        try
        {
            List list=postMapper.querySomePartPost(start,end);
            System.out.println(list);
            return list;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<PartPost> queryPartPostByZone(int start, int end, String zone) {
        List returnList=null;
        try
        {
            List list=postMapper.queryAllPartPostByZone(zone);
            //如果开头就大于 list的大小 那么就返回空
            if(start>list.size())
                return null;
            //如果尾部大于长度 则 返回 最大长度
            end=end>list.size()?list.size():end;
            returnList=list.subList(start,end);
            return returnList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<PartPost> queryPartPostByKeyWord(String keyword) {
        List ansList=null;
        try
        {
            ansList=postMapper.queryAllPartPostByKeyword(keyword);
            return ansList;
        }catch (Exception e)
        {
            return ansList;
        }
    }

    @Override
    public List<PartPost> queryPartPostByZoneAndKeyWord(String zone, String keyword) {
        List ansList=null;
        try
        {
            //如果是所有区域 则 只搜寻关键字
            if(zone.equals("全部"))
                ansList=postMapper.queryAllPartPostByKeyword(keyword);
            //如果是特定区域 则 搜索该区域和关键字
            else
                ansList=postMapper.queryAllPartPostByZoneAndKeyWord(zone,keyword);
            System.out.println(zone+keyword);
            return ansList;
        }catch (Exception e)
        {
            return ansList;
        }
    }

    @Override
    public List<MyPublishPost> queryMyPublishPostByUserId(Integer userId) {
        List ansList=null;
        try
        {
            ansList=postMapper.queryMyPublishPostByUserId(userId);
            return ansList;
        }catch (Exception e)
        {
            return ansList;
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
