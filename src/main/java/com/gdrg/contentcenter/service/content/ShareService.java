package com.gdrg.contentcenter.service.content;

import com.gdrg.contentcenter.dao.share.SharecontentMapper;
import com.gdrg.contentcenter.domain.dto.share.ShareDTO;
import com.gdrg.contentcenter.domain.dto.user.UserDTO;
import com.gdrg.contentcenter.domain.entity.share.Sharecontent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final SharecontentMapper sharecontentMapper;
    private final RestTemplate restTemplate;//由于在应用里面，已经将这个bean注入容器了，可以直接使用

    public ShareDTO findById(Integer id){
        //获取分享详情
        Sharecontent  sharecontent=this.sharecontentMapper.selectByPrimaryKey(id);
        Integer user_id=sharecontent.getUserId();

        //这里怎么调用用户微服务的/users/{user_id}
        //注意这里的restTemplate是从容器里面取出来的，在构造的时候取的，autowired写在构造注解里了
        UserDTO userDTO=this.restTemplate.getForObject(
                "http://localhost:8080/user/"+id.toString(),
                UserDTO.class
        );

        //第三步就是消息的装配
        ShareDTO shareDTO=new ShareDTO();
        BeanUtils.copyProperties(sharecontent,shareDTO);//自动把sharecontent里面的属性数据拷贝到shareDTO里面
        shareDTO.setNickName(userDTO.getUsername());//然后从userDTO里面获取用户名填进去



        return shareDTO;
    }


}
