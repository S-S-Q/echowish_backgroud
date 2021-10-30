package com.echowish.android_backgroud.pojo.comment;

import com.echowish.android_backgroud.pojo.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MyComment {
    public Integer postId;
    public Integer userId;
    public String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date time;
    public String postTitle;

    public MyComment(Comment comment, String postTitle)
    {
        this.postId=comment.getPostId();
        this.userId=comment.getUserId();
        this.content=comment.getContent();
        this.time=comment.getTime();
        this.postTitle=postTitle;
    }
}
