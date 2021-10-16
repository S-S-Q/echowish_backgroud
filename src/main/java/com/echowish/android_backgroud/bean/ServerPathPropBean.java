package com.echowish.android_backgroud.bean;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
//告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
//prefix = "locatepath"：配置文件中哪个下面的所有属性进行一一映射
@ConfigurationProperties(prefix = "locatepath")
//指定加载配置文件的位置
@PropertySource(value = "classpath:locatepath.properties")
@Order(1)
public class ServerPathPropBean {

    private String serverUrl;
    private String imagePath;
    private String imageMapping;
    private String headImagePath;
    private String headImageMapping;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageMapping() {
        return imageMapping;
    }

    public void setImageMapping(String imageMapping) {
        this.imageMapping = imageMapping;
    }

    public String getHeadImagePath() {
        return headImagePath;
    }

    public void setHeadImagePath(String headImagePath) {
        this.headImagePath = headImagePath;
    }

    public String getHeadImageMapping() {
        return headImageMapping;
    }

    public void setHeadImageMapping(String headImageMapping) {
        this.headImageMapping = headImageMapping;
    }
}