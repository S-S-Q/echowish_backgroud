package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.AdminMapper;
import com.echowish.android_backgroud.pojo.admin.Admin;
import com.echowish.android_backgroud.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public String registerNewAdmin(Admin admin) {
        try
        {
            adminMapper.insertNewAdmin(admin);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String deleteAdmin(Integer id) {
        try
        {
            adminMapper.deleteAdminById(id);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public Integer load(String account, String password) {
        try
        {
            return adminMapper.queryAdminNameByAccountAndPassword(account,password);
        }
        catch (Exception e)
        {
            return -1;
        }
    }
}
