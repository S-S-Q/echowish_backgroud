package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.preconcern.MyPreConcernRequest;

import java.util.List;

public interface PreConcernService {
    public String sendConcernRequest(int hostId,int friId);

    public String acceptConcernRequest(int hostId,int friId);

    public String refuseConcernRequest(int hostId,int friId);

    public String deleteConcernRequestByUserId(int userId);

    public List<MyPreConcernRequest> queryAllMyPreConcern(int hostId);
}
