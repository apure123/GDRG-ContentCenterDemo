package com.gdrg.contentcenter.domain.dto.share;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareDTO {
    private Integer id;

    private String content;

    private Integer price;

    private String title;

    private Integer userId;

    private String nickName;//昵称，从用户表里面的用户名获取

}
