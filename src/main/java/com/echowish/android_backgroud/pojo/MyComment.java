package com.echowish.android_backgroud.pojo;

import java.util.Date;

public class MyComment {
    public Integer postId;
    public Integer userId;
    public String content;
    public Date time;
    public String postTitle;

    public MyComment(Comment comment,String postTitle)
    {
        this.postId=comment.getPostId();
        this.userId=comment.getUserId();
        this.content=comment.getContent();
        this.time=comment.getTime();
        this.postTitle=postTitle;
    }
}
