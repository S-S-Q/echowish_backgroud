package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.MyPreConcernRequest;

import java.util.List;

public interface PreConcernService {
    public String sendConcernRequest(int hostId,int friId);

    public String acceptConcernRequest(int hostId,int friId);

    public String refuseConcernRequest(int hostId,int friId);

    public List<MyPreConcernRequest> queryAllMyPreConcern(int hostId);
}
