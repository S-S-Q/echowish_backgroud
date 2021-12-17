package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.post.Collection;

public interface CollectionService {
    String collectPost(Collection collection);

    Boolean queryIsCollect(Collection collection);

    String cancelCollection(Collection collection);
}
