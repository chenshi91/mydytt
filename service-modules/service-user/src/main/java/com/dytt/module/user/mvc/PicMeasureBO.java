package com.dytt.module.user.mvc;

import lombok.Getter;

/**
 * @author zhengmingdong
 * @date 2019-03-30 17:43
 */
@Getter
public class PicMeasureBO {

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片高度
     */
    private Integer height;

    public PicMeasureBO() {
    }

    public PicMeasureBO(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }
}
