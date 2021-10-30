package com.echowish.android_backgroud.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public Integer userId;
    public String account;
    public String password;
    public String name;
    public Integer sex;
    public Integer grade;
    public String campus;
    public String headImage;
}
