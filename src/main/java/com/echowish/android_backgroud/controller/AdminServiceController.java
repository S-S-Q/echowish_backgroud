package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.admin.Admin;
import com.echowish.android_backgroud.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class AdminServiceController {
    @Autowired
    AdminService adminService;

    @GetMapping("register")
    @ResponseBody
    public String registerNewAdmin(@RequestParam(value = "account",required = true) String account,
                                   @RequestParam(value = "password",required = true)String password)
    {
        Admin admin=new Admin();
        admin.setAccount(account);
        admin.setPassword(password);
        return adminService.registerNewAdmin(admin);
    }

    @GetMapping("load")
    @ResponseBody
    public Integer load(@RequestParam(value = "account",required = true) String account,
                        @RequestParam(value = "password",required = true)String password)
    {
        return  adminService.load(account,password);
    }

    @GetMapping("delete")
    @ResponseBody
    public String deleteAdmin(@RequestParam(value = "id",required = true)Integer admin_id)
    {
        return adminService.deleteAdmin(admin_id);
    }
}
