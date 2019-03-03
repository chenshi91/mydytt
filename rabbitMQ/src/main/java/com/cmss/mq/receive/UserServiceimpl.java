/* created by chenshi at 2018-10-29 */
package com.cmss.mq.receive;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 测试异步方法调用
 */
@Service
public class UserServiceimpl implements UserService {


    @Override
    public void yibu() {
        try {
            System.out.println("22222222222222222222222222");
            Thread.sleep(7000);
            if (1 == 1) {
                System.out.println("222222222222223333333333333");
                throw new RuntimeException();
            }
            System.out.println("33333333333333333333333333");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async()
    @Override
    public void yibu2() {
        this.yibu();
    }

}
