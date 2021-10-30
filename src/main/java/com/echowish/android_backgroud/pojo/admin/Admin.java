package com.echowish.android_backgroud.pojo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    Integer adminId;
    String account;
    String password;
    String name;
}
