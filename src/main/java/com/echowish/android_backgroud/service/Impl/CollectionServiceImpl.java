package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.CollectionsMapper;
import com.echowish.android_backgroud.pojo.post.Collection;
import com.echowish.android_backgroud.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionsMapper collectionsMapper;

    @Override
    public String collectPost(Collection collection) {
        try {
            collectionsMapper.collectPost(collection);
            return ReactInfo.SUCCESS_INFO;
        }catch (Exception e)
        {
            log.info("收藏帖子出错"+e);
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public Boolean queryIsCollect(Collection collection) {
        try {
            if(collectionsMapper.queryIsCollections(collection)>=1)
            return true;
            else
                return false;
        }catch (Exception e)
        {
            log.info("获取是否收藏出错"+e);
            return false;
        }
    }

    @Override
    public String cancelCollection(Collection collection) {
        try {
            collectionsMapper.cancelCollection(collection);
            return ReactInfo.SUCCESS_INFO;
        }catch (Exception e)
        {
            log.info("取消收藏出错"+e);
            return ReactInfo.FAIL_INFO;
        }
    }
}
