package com.echowish.android_backgroud.pojo.comment;

import com.echowish.android_backgroud.pojo.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAndUserInfo {
    public Integer postId;
    public Integer userId;
    public String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date time;
    public String name;
    public String headImage;

    public CommentAndUserInfo(Comment comment, String username, String headImage)
    {
        this.postId=comment.getPostId();
        this.userId=comment.getUserId();
        this.content=comment.getContent();
        this.time=comment.getTime();
        this.name=username;
        this.headImage=headImage;
    }
}
