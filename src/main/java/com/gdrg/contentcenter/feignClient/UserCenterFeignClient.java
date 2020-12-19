package com.gdrg.contentcenter.feignClient;

import com.gdrg.contentcenter.configuration.UserCenterFeignConfiguration;
import com.gdrg.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//这里指定需要请求的微服务的名称
@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
public interface UserCenterFeignClient {

    /**
     * 当findById被调用的时候
     * feign就会构造出"http://user-center/{id}"这样的url，并且去请求
     * 返回的结果转换成UserDTO格式
     *
     * */

    //在这里直接写http请求的方法了
    @GetMapping("user/{id}")
    UserDTO findById(@PathVariable Integer id);

}
