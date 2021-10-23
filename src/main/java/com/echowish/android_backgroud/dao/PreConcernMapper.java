package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.MyConcern;
import com.echowish.android_backgroud.pojo.MyPreConcernRequest;
import com.echowish.android_backgroud.pojo.PreConcern;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PreConcernMapper {
    void insertPreConcern(@Param(value = "hostId") Integer hostId,
                       @Param(value = "friId") Integer friId);

    void cancelPreConcern(@Param(value = "hostId")Integer hostId,
                       @Param(value = "friId")Integer friId);

   PreConcern queryIsPreConcern(@Param(value = "hostId")Integer hostId,
                                @Param(value = "friId")Integer friId);

    List<MyPreConcernRequest> queryAllMyPreConcern(@Param(value = "hostId")Integer hostId);
}
