package com.echowish.android_backgroud.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartPost {
    public Integer postId;
    public String zone;
    public String title;
    public String content;
}
