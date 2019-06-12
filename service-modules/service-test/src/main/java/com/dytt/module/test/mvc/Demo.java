package com.dytt.module.test.mvc;


import com.dytt.common.mvc.IdEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

//import org.hibernate.validator.constraints.Length;
//import javax.validation.constraints.Max;

@Data
public class Demo extends IdEntity {

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createDate;

    private String createBy;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss", timezone = "")
    private Date updateDate;

    private String updateBy;

    //    @Max(value = 5,message = "名字长度不能超过5")
    private String name;

    @JsonIgnore
    private Integer age;

    //    @JsonIgnore
//    @Length(max = 20,min = 5,message = "地址长度在5~20")
    private String address;

    private String phone;

}