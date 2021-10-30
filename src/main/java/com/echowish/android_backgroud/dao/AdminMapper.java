package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.admin.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    void insertNewAdmin(Admin admin);

    void deleteAdminById(Integer id);

    Admin queryAdminByAccount(String account);

    Integer queryAdminNameByAccountAndPassword(@Param("account") String account, @Param("password")String password);
}
