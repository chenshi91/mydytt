package com.dytt.module.test.entity;

import com.dytt.common.mvc.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenshi
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Users extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String phone;

    private String nickName;

    /**
     * 0未知,1男2女
     */
    private Boolean sex;


}
