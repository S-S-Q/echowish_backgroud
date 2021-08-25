package com.echowish.android_backgroud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
//上面两个注释分别是自动创建构造函数 以及有参 无参构造函数
public class Post {
  public Integer postId;
  public Integer userId;
  public String title;
  public String content;
  public String reward;
  public String imageUri;
  public Date time;

  public Post(Integer userId,String title,String content,String reward,String imageUri,Date time)
  {
    this.userId=userId;
    this.title=title;
    this.content=content;
    this.reward=reward;
    this.imageUri=imageUri;
    this.time=time;
  }


}
