package com.dytt.gateway.module;

import com.dytt.gateway.base.GatewayBaseTest;
import org.junit.Test;

/**
 * @author chenshi
 * @date 2019-06-13
 */
public class Movie  extends GatewayBaseTest {

    @Test
    public void 查询(){
        super.httpRequestOfGET("/movie/page?pageNo={pageNo}&pageSize={pageSize}&movieName={movieName}",
                1,10,"功夫");
    }

    @Test
    public void mono(){
        System.out.println("sssyy");
        super.httpRequestOfGET("/fallBack");
    }

    @Test
    public void 添加(){
//        super.httpRequestOfPost("/es/redis/add","redis/add");
    }

    @Test
    public void 删除(){
//        super.httpRequestOfGet("/es/redis/delete?key={key}","name");
    }
}
