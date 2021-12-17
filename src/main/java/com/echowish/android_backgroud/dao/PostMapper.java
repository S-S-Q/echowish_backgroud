package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.post.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostMapper {
    //向数据库中插入新的帖子
    public void insertPost(Post post);

    //查找所有帖子 详细信息
    public List<Post> queryAllPost();

    //通过用户id删除帖子
    public void deleteByUserId(Integer userId);

   //通过帖子id查找图片路径
    public String queryImageByPostId(Integer postId);

   //通过帖子id删除帖子
    public void deleteByPostId(Integer postId);

    //获取帖子所有信息
    public Post queryPost(Integer postId);

    //获取帖子以及发布者信息
    public DetailPost queryDetailPost(Integer postId);

    //通过帖子ID获取标题
    public String queryPostTitleByPostId(Integer postId);

    //获取帖子部分信息 用于首页展示
    public List<PartPost> queryAllPartPost();


    //获取部分帖子信息 用于首页展示
    public List<PartPost> querySomePartPost(@Param(value = "start")Integer start,
                                             @Param(value = "end")Integer end);

    //获取帖子的部分信息 通过分区
    public  List<PartPost> queryAllPartPostByZone(String zone);

    //通过关键字获取帖子部分信息
    public List<PartPost>  queryAllPartPostByKeyword(String keyword);


    //通过关键字 或者分区 获取帖子的部分信息
    public List<PartPost> queryAllPartPostByZoneAndKeyWord(@Param(value = "zone") String zone,
                                                           @Param(value = "keyword") String keyword);

    public List<MyPublishPost> queryMyPublishPostByUserId(Integer userId);

    //通过userId 获取用户发布帖子的postId
    public List<Integer> queryMyPostIdByUserId(Integer userId);

    // 修改帖子访问量
    public void updatePostVisits(Integer postId);

    //修改帖子的收藏量
    public void updatePostCollections(Integer postId);

    //获取热搜帖子
    public List<HotSearch> getHotSearch(Integer my_num);

}
