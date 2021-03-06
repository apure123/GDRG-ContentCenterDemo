package com.gdrg.contentcenter.domain.entity.share;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shareContent")
public class Sharecontent {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String content;

    private Integer price;

    private String title;

    @Column(name = "user_id")
    private Integer userId;


}