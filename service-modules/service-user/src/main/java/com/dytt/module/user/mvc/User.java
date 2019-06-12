/*created by chenshi at   2019/5/8*/
package com.dytt.module.user.mvc;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dytt.common.mvc.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chenshi
 * @date 2019-05-08
 */
@TableName("users")
@Data
public class User extends BaseEntity {

    private Long phone;
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    private Byte sex;

}
