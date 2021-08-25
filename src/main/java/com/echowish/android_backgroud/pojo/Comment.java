package com.echowish.android_backgroud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    public Integer postId;
    public Integer userId;
    public String content;
    public Date time;
}
