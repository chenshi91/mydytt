package com.dytt.module.demo.mvc;


import com.dytt.common.mvc.IdEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Demo extends IdEntity {
    private Long id;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private String name;

    private Integer age;

    private String address;

    private String phone;

}