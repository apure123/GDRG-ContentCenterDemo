package com.gdrg.contentcenter.configuration;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;

public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        //读取配置文件，并初始化NacosWeightedRule，这里不需要
    }

    @Override
    public Server choose(Object key) {
        //这个是需要实现的方法
        //首先获取loadBalancer，这是ribbon的入口
        BaseLoadBalancer loadBalancer =(BaseLoadBalancer)this.getLoadBalancer();
        //想要请求的微服务的名称
        String name=loadBalancer.getName();

        //实现负载均衡算法



        return null;
    }
}
