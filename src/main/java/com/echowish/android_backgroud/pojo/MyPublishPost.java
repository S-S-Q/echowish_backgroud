package com.echowish.android_backgroud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPublishPost {
    public Integer postId;
    public String zone;
    public String title;
    public String content;
    public Date time;
}
