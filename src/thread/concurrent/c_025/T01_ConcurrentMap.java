package thread.concurrent.c_025;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 同步容器类
 * 1：Vector Hashtable ：早期使用synchronized实现
 * 2：ArrayList HashMap ：未考虑多线程安全（未实现同步）
 * 3：HashMap vs Hashtable   StringBuilder vs StringBuffer
 * 4：Collections.synchronized***工厂方法使用的也是synchronized，将传进来的非线程安全容器包装成线程安全的
 * <p>
 * 使用早期的同步容器以及Collections.synchronized***方法的不足之处，请阅读：
 * http://blog.csdn.net/itm_hadf/article/details/7506529
 * <p>
 * 使用新的并发容器
 * http://xuganggogo.iteye.com/blog/321630
 * <p>
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 */

public class T01_ConcurrentMap {

    public static void main(String[] args) {

//        Map<String, String> map = new ConcurrentHashMap<>();  // 相比Hashtable，锁的粒度更小（分成16段）
//        Map<String, String> map = new ConcurrentSkipListMap<>(); // 高并发并且排序

        Map<String, String> map = new Hashtable<>();
//        Map<String, String> map = new HashMap<>(); //Collections.synchronizedXXX
        // TreeMap
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);  // 准备一个门闩，计数器100
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {      // 100个线程
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {   // 每个线程向Map中装入10000个随机字符串
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
