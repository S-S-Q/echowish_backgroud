package com.echowish.android_backgroud.pojo.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCollection {
    public Integer collectionId;
    public Integer postId;
    public Integer userId;
    public String zone;
    public String title;
    public String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date time;
}
