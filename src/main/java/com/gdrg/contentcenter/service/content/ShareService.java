package com.gdrg.contentcenter.service.content;

import com.gdrg.contentcenter.dao.share.SharecontentMapper;
import com.gdrg.contentcenter.domain.dto.share.ShareDTO;
import com.gdrg.contentcenter.domain.dto.user.UserDTO;
import com.gdrg.contentcenter.domain.entity.share.Sharecontent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final SharecontentMapper sharecontentMapper;
    private final RestTemplate restTemplate;//由于在应用里面，已经将这个bean注入容器了，可以直接使用
    private final DiscoveryClient discoveryClient;//首先注入服务发现，用来获取用户服务的ip地址

    public ShareDTO findById(Integer id){
        //获取分享详情
        Sharecontent  sharecontent=this.sharecontentMapper.selectByPrimaryKey(id);
        Integer user_id=sharecontent.getUserId();

        //整合ribbon之后这些都不需要了
        /*//通过服务发现客户端对象可以拿到服务的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");

        //拿到所有用户中心的请求地址
        List<String> targetUrls=instances.stream()
                .map(instance-> instance.getUri().toString()+"/user/"+id.toString())
                .collect(Collectors.toList());
        //写随机算法，获取一个下标
        int i = ThreadLocalRandom.current().nextInt(targetUrls.size());*/


        //这里怎么调用用户微服务的/users/{user_id}
        //注意这里的restTemplate是从容器里面取出来的，在构造的时候取的，autowired写在构造注解里了
        //ribbon会自动把user-center转换成这个服务在nacos上面的某个实例的ip
        UserDTO userDTO=this.restTemplate.getForObject(
                "http://user-center/user/"+id.toString(),
                UserDTO.class
        );

        //第三步就是消息的装配
        ShareDTO shareDTO=new ShareDTO();
        BeanUtils.copyProperties(sharecontent,shareDTO);//自动把sharecontent里面的属性数据拷贝到shareDTO里面
        shareDTO.setNickName(userDTO.getUsername());//然后从userDTO里面获取用户名填进去



        return shareDTO;
    }


}
