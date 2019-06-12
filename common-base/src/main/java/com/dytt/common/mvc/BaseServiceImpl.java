/* created by chenshi at 2018-11-16 */
package com.dytt.common.mvc;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author 39248
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

}
