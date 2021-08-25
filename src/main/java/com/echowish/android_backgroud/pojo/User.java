package com.echowish.android_backgroud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public String headImageUri;
}
