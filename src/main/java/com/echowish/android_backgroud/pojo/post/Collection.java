package com.echowish.android_backgroud.pojo.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    public Integer collectionId;
    public Integer postId;
    public Integer userId;
}
