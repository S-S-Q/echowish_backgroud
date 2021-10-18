package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.MyConcern;

import java.util.List;

public interface ConcernService {

    //关注新的好友
    String concernNewFriend(Integer hostId,Integer friId);

    //取消关注好友
    String cancelFriend(Integer hostId,Integer friId);

    //获得我的关注
    List<MyConcern> getAllMyConcern(Integer hostId);

    //获取是否关注
    Boolean queryIsConcern(Integer hostId,Integer friId);
}
