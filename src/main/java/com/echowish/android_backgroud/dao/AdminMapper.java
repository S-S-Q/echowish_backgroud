package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.admin.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper
public interface AdminMapper {
    void insertNewAdmin(Admin admin);

    void deleteAdminById(Integer id);

    Admin queryAdminByAccount(String account);

    Integer queryAdminNameByAccountAndPassword(@Param("account") String account, @Param("password")String password);
}
