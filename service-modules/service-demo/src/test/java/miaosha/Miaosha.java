/*created by chenshi at   2019/4/18*/
package miaosha;

import com.dytt.common.test.BaseTest;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 秒杀系统
 */
public class Miaosha extends BaseTest {

    @Test
    public void miaosha() {
        final String watchkeys = "watchkeys";
        ExecutorService executor = Executors.newFixedThreadPool(20);

//        final Jedis jedis = new Jedis("localhost", 6379);
//        jedis.set(watchkeys, "0");// 重置watchkeys为0
//        jedis.del("setsucc", "setfail");// 清空抢成功的，与没有成功的
//        jedis.close();
//
//        for (int i = 0; i < 10000; i++) {// 测试一万人同时访问
//            executor.execute(new MyRunnable());
//        }
//        executor.shutdown();
    }
}
