package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.admin.Admin;

public interface AdminService {
    String registerNewAdmin(Admin admin);

    String deleteAdmin(Integer id);

    Integer load(String account,String password);
}
