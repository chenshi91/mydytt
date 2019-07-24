package com.dytt.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chenshi
 * @date 2019-07-11
 */
@Component
@Slf4j
public class RedisLockUtil {

    @Autowired
    RedisTemplate   redisTemplate;

    public Boolean  addLock(String key,String  value){
        if (redisTemplate.opsForValue().setIfAbsent(key,value)) {
            log.info("------------获取redis分布式锁成功!");
            return true;
        }
        log.info("------------获取redis分布式锁失败!");
        return false;
    }

    public Boolean unLock(String   key,String  value){
        String curValue = (String) redisTemplate.opsForValue().get(key);
        if (!StringUtil.isNull(curValue)&&curValue.equals(value)) {
            Boolean deleteResult = redisTemplate.opsForValue().getOperations().delete(key);
            log.info("------------释放redis分布式锁:{}",deleteResult);
            return deleteResult;
        }
        return false;
    }

    public Boolean  doServiceWithRedislock(String   key,String  value,Runnable  service){
        if (addLock(key,value)) {
            try {
                service.run();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                unLock(key,value);
            }

        }
        return false;
    }

}
