package com.echowish.android_backgroud.pojo.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotSearch {
    public Integer rank;
    public Integer postId;
    public String title;
}
