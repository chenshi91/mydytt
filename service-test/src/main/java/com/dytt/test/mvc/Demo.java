package com.dytt.test.mvc;


import com.dytt.common.model.mvc.IdEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Demo extends IdEntity {

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createDate;

    private String createBy;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss", timezone = "")
    private Date updateDate;

    private String updateBy;

    @JsonIgnore
    private String name;

    @JsonIgnore
    private Integer age;

    @JsonIgnore
    private String address;

    private String phone;

}