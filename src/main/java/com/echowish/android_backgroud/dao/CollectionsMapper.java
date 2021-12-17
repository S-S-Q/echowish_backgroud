package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.post.Collection;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectionsMapper {

    void collectPost(Collection collection);

    Integer queryIsCollections(Collection collection);

    void cancelCollection(Collection collection);
}
