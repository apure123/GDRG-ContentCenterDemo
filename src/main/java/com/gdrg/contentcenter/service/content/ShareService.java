package com.gdrg.contentcenter.service.content;

import com.gdrg.contentcenter.dao.share.SharecontentMapper;
import com.gdrg.contentcenter.domain.dto.share.ShareDTO;
import com.gdrg.contentcenter.domain.dto.user.UserDTO;
import com.gdrg.contentcenter.domain.entity.share.Sharecontent;
import com.gdrg.contentcenter.feignClient.UserCenterFeignClient;
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
    /*private final RestTemplate restTemplate;//由于在应用里面，已经将这个bean注入容器了，可以直接使用*/
    /*private final DiscoveryClient discoveryClient;//首先注入服务发现，用来获取用户服务的ip地址*/
    private final UserCenterFeignClient userCenterFeignClient;


    public ShareDTO findById(Integer id){
        //获取分享详情
        Sharecontent  sharecontent=this.sharecontentMapper.selectByPrimaryKey(id);
        Integer user_id=sharecontent.getUserId();

        //用feign重构之后，不使用restTemplate，就只需要这一行语句
        UserDTO userDTO = this.userCenterFeignClient.findById(user_id);

        //第三步就是消息的装配
        ShareDTO shareDTO=new ShareDTO();
        BeanUtils.copyProperties(sharecontent,shareDTO);//自动把sharecontent里面的属性数据拷贝到shareDTO里面
        shareDTO.setNickName(userDTO.getUsername());//然后从userDTO里面获取用户名填进去



        return shareDTO;
    }


}
