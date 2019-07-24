package com.dytt.gateway.module;

import com.dytt.gateway.base.GatewayBaseTest;
import org.junit.Test;

import java.util.Random;

/**
 * @author chenshi
 * @date 2019-06-13
 */
public class Redis  extends GatewayBaseTest {

    @Test
    public void 添加(){
        super.httpRequestOfPOST("/es/redis/add","redis/add");
    }

    @Test
    public void 删除(){
        super.httpRequestOfGET("/es/redis/delete?key={key}","name");
    }

    @Test
    public void 查询(){
        super.httpRequestOfGET("/es/redis/select?key={key}&type={type}",
                "age","string");
    }

    @Test
    public void aa(){
        for (int i = 0; i < 20; i++) {
            System.out.println(new Random().nextInt(5));
        }
    }

}
