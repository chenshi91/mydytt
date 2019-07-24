package com.dytt.module.es.resource;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.mvc.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author chenshi
 * @date 2019-06-13
 */
@Slf4j
@RequestMapping(produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
@RestController
public class RedisController {

    @Autowired
    RedisTemplate   redisTemplate;

    @PostMapping(value = {"/redis/add"})
    public ResponseResult   add(@RequestBody JSONObject  params){
        Set<String> keySet = params.keySet();
        Long expire = params.getLong("expire");
        for (String key : keySet) {
            Object value = params.get(key);
            if (value instanceof String||value  instanceof Integer) {
                redisTemplate.opsForValue().set(key,value);
            }else if (value instanceof HashMap) {
                redisTemplate.opsForHash().putAll(key, (Map) value);
            }else if (value instanceof Set) {
                redisTemplate.opsForSet().add(key,value);
            }else if (value instanceof List) {
                redisTemplate.opsForList().rightPush(key,value);
            }
            if (expire==null) {
                continue;
            }
            redisTemplate.expire(key,expire,TimeUnit.MINUTES);
        }
        return new ResponseResult("添加redis成功!");
    }

    @GetMapping(value = {"/redis/delete"})
    public ResponseResult   delete(@RequestParam String   key){
        Boolean deleteResult = redisTemplate.delete(key);
        return new ResponseResult(deleteResult);
    }

    @GetMapping(value = {"/redis/select"})
    public ResponseResult   select(@RequestParam String   key
            ,@RequestParam(value = "type",required = false) String    type){
        Object value=null;
        if (type==null) {
            type="";
        }
        switch (type) {
            case "map":
                value = redisTemplate.opsForHash().entries(key);
                break;
            case "list":
                value = redisTemplate.opsForList().range(key,0,-1);
                break;
            case "set":
                value = redisTemplate.opsForSet().members(key);
                break;
            default:
                value = redisTemplate.opsForValue().get(key);
                if (value   instanceof Integer) {
                    redisTemplate.opsForValue().increment(key);
                }
                break;
        }
        Long expire = redisTemplate.getExpire(key, TimeUnit.MINUTES);
        Object finalValue = value;
        return new ResponseResult(new HashMap<String,Object>(3){{
            put("key",key);
            put("value", finalValue);
            put("expire",expire);
        }});
    }

}
