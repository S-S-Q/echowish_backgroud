package com.echowish.android_backgroud.pojo.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
//上面两个注释分别是自动创建构造函数 以及有参 无参构造函数
public class Post {
  public Integer postId;
  public Integer userId;
  public String zone;
  public String title;
  public String content;
  public String reward;

  public String postImage;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  public Date time;
  public Integer visits;
  public Integer collections;

  public Post(Integer userId,String title,String zone,String content,String reward,String imageUri,Date time)
  {
    this.userId=userId;
    this.title=title;
    this.zone=zone;
    this.content=content;
    this.reward=reward;
    this.postImage=imageUri;
    this.time=time;
  }


}
