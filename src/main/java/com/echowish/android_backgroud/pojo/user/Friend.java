package com.echowish.android_backgroud.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    public Integer userId;
    public String name;
    public Integer sex;
    public Integer grade;
    public String campus;
    public String headImage;
    public boolean isConcern;
}
