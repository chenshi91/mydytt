package com.dytt.module.test.mvc;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author chenshi
 * @date 2019-11-10
 */
@Data
public class DemoForm {

    @NotBlank
    @Length(max = 10,min = 2,message = "名字长度2-10")
    private String name;

    @Max(200)
    @Min(1)
    private Integer age;

    @Size(max = 20,min = 5,message = "地址长度在5~20")
    private String address;


    private String phone;
}
