package com.echowish.android_backgroud.pojo.comment;


import com.echowish.android_backgroud.pojo.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OthersComment {
    public Integer postId;
    public Integer userId;
    public String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date time;
    public User user;
}
