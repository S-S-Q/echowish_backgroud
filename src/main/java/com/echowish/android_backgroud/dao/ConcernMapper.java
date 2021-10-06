package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.Concern;
import com.echowish.android_backgroud.pojo.Friend;
import com.echowish.android_backgroud.pojo.MyConcern;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcernMapper {
    void insertConcern(@Param(value = "hostId") Integer hostId,
                       @Param(value = "friId") Integer friId);

    void cancelConcern(@Param(value = "hostId")Integer hostId,
                       @Param(value = "friId")Integer friId);

    Concern queryIsConcern(@Param(value = "hostId")Integer hostId,
                           @Param(value = "friId")Integer friId);

    List<MyConcern> queryAllMyConcern(@Param(value = "hostId")Integer hostId);
}
