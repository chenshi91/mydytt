/**
 * Created	by	chenshi  at	2017年12月1日 上午10:54:10
 */
package com.dytt.common.model.mvc;

import java.io.Serializable;

/**
 * @description: IdEntity.java
 * @packageName: com.quanhu.activie.base.entity
 * @projectName: common-base
 * @revision: v1.0.0
 * @author: chenshi
 */
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = 2748105371639838785L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
