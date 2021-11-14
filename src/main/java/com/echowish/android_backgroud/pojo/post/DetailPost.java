package com.echowish.android_backgroud.pojo.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailPost {
    public Integer postId;
    public Integer userId;
    public String zone;
    public String title;
    public String content;
    public String reward;
    public String postImage;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date time;
    public String name;
    public String headImage;
}
