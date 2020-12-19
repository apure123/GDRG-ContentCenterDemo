package com.gdrg.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;



public class UserCenterFeignConfiguration {
    //可以自己在这里自定义日志级别了
    @Bean
    public Logger.Level level(){
        //让feign打印所有请求的细节
        return Logger.Level.FULL;
    }
}
