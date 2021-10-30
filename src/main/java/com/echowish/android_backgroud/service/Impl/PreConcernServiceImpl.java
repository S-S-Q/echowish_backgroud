package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.PreConcernMapper;
import com.echowish.android_backgroud.pojo.preconcern.MyPreConcernRequest;
import com.echowish.android_backgroud.service.ConcernService;
import com.echowish.android_backgroud.service.PreConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreConcernServiceImpl implements PreConcernService {

    @Autowired
    PreConcernMapper preConcernMapper;
    @Autowired
    ConcernService concernService;

    @Override
    public String sendConcernRequest(int hostId, int friId) {
        try
        {
            preConcernMapper.insertPreConcern(hostId,friId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String acceptConcernRequest(int hostId, int friId) {
        try
        {
            preConcernMapper.cancelPreConcern(hostId,friId);

            try {
                preConcernMapper.cancelPreConcern(friId,hostId);
            }
            catch (Exception e)
            {

            }
            //这里可以在chat数据库里面发个信息
            concernService.concernNewFriend(hostId,friId);
            concernService.concernNewFriend(friId,hostId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String refuseConcernRequest(int hostId, int friId) {
        try
        {
            preConcernMapper.cancelPreConcern(hostId,friId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String deleteConcernRequestByUserId(int userId) {
        try
        {
            preConcernMapper.deleteByFriIdOrHostId(userId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public List<MyPreConcernRequest> queryAllMyPreConcern(int hostId) {
        List<MyPreConcernRequest> myPreConcernRequests=null;
        try
        {
            myPreConcernRequests=preConcernMapper.queryAllMyPreConcern(hostId);
            return myPreConcernRequests;
        }
        catch (Exception e)
        {
            return myPreConcernRequests;
        }
    }
}
