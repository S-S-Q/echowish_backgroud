package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.ConcernMapper;
import com.echowish.android_backgroud.pojo.Concern;
import com.echowish.android_backgroud.pojo.MyConcern;
import com.echowish.android_backgroud.service.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcernServiceImpl implements ConcernService {
    @Autowired
    ConcernMapper concernMapper;


    @Override
    public String concernNewFriend(Integer hostId, Integer friId) {
        try {
            System.out.println(hostId+friId);
            concernMapper.insertConcern(hostId,friId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String cancelFriend(Integer hostId, Integer friId) {
        try{
            concernMapper.cancelConcern(hostId,friId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public List<MyConcern> getAllMyConcern(Integer hostId) {
        List<MyConcern>myConcernList=null;
        try
        {
            myConcernList=concernMapper.queryAllMyConcern(hostId);
            return myConcernList;
        }
        catch (Exception e)
        {
            return myConcernList;
        }
    }

    @Override
    public Boolean queryIsConcern(Integer hostId, Integer friId) {
        try {
            Concern concern=concernMapper.queryIsConcern(hostId,friId);
            if (concern!=null)
                return  true;
            else
                return false;
        }
        catch (Exception e)
        {
            return  false;
        }
    }
}
