package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.bean.ServerPathPropBean;
import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.pojo.post.DetailPost;
import com.echowish.android_backgroud.pojo.post.MyPublishPost;
import com.echowish.android_backgroud.pojo.post.PartPost;
import com.echowish.android_backgroud.pojo.post.Post;
import com.echowish.android_backgroud.service.CommentService;
import com.echowish.android_backgroud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;
    @Autowired
    CommentService commentService;
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
                System.out.println(true);
                postMapper.insertPost(post);
                return ReactInfo.SUCCESS_INFO;
            }
            else
            {
                System.out.println(false);
                deleteImage(post.postImage);
                return ReactInfo.FAIL_INFO;
            }

        }
        catch (Exception e) {
            System.out.println(false+"还有错误");
            System.out.println(e);
           return ReactInfo.FAIL_INFO;
        }
    }

    //删帖子记得删图片
    @Override
    public String deletePost(int postId) {
        try {
            commentService.deleteAllCommentByPostId(postId);
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
            System.out.println(e);
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
    public String deletePostByUserId(int userId) {
        try
        {
            postMapper.deleteByUserId(userId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
//    @Cacheable(cacheNames = "post",key = "#postId")
    public Post queryPost(Integer postId) {
        Post post=null;
        try
        {
            if(postId==null)
                return null;
            post=postMapper.queryPost(postId);
            System.out.println("调用了");
            return post;
        }
        catch (Exception e)
        {
            return post;
        }
    }

    @Override
    public List<Post> queryAllPost() {
        try
        {
            return postMapper.queryAllPost();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public String queryPostTitleByPostId(Integer postId) {
        String title=null;
        try
        {
            title=postMapper.queryPostTitleByPostId(postId);

        }
        catch (Exception e)
        {

        }
        return  title;
    }

    @Override
    public List<Integer> queryMyPostIdByUserId(Integer userId) {
        List<Integer>myPostIdList=new LinkedList<>();
        try {
            myPostIdList=postMapper.queryMyPostIdByUserId(userId);
        }
        catch (Exception e)
        {

        }
        return myPostIdList;
    }

    @Override
//    @Cacheable(cacheNames = "detailPost",key = "#postId")
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
}
