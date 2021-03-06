package com.echowish.android_backgroud.pojo.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date time;
}
