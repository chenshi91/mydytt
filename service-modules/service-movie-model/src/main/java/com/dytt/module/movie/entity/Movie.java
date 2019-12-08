package com.dytt.module.movie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("gcdy_movie")
@Data
public class Movie implements Serializable {

    private Long id;

    private Integer createUserId;

    private Date createDate;

    private Integer lastUpdateUserId;

    private Date lastUpdateDate;

    private String title;

    private String name;

    private String name2;

    private String age;

    private String placeOfOrigin;

    private String categoryId;

    private String language;

    private String subtitle;

    private String releaseTime;

    private String doubanScore;

    private String imdbScore;

    private String fileFormat;

    private String videoSize;

    private String fileSize;

    private String length;

    private String director;

    private String actor;

    private String summary;

    private String awards;

    private String downloadLink;

    private String imgUrl;


}