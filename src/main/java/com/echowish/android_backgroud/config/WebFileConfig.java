package com.echowish.android_backgroud.config;

import com.echowish.android_backgroud.bean.ServerPathPropBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebFileConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ServerPathPropBean configBeanProp;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //其中static或images表示访问的前缀。"configBeanProp.getImagePath()"是文件真实的存储路径  /image/**
        registry.addResourceHandler(configBeanProp.getImageMapping() + "**").addResourceLocations("file:" + configBeanProp.getImagePath());
        //上传的头像在configBeanProp.getAvatarPath()目录下   /avatar/**
        registry.addResourceHandler(configBeanProp.getHeadImageMapping()+ "**").addResourceLocations("file:" + configBeanProp.getHeadImagePath());
    }
}
