package com.gdrg.contentcenter.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonConfiguration.RibbonConfiguration;

/*@Configuration
//表明这个类是为用户中心服务的配置
@RibbonClient(name="user-center",configuration = RibbonConfiguration.class)*/


@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {
}
