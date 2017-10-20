package thread.concurrent.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 利用阻塞队列实现生产者消费者模型
 * ArrayBlockingQueue —— 有界阻塞队列
 */

public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10); // 有界阻塞队列，此处最多装10个

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        strs.put("a11");        // 满了再加就会阻塞
//        strs.add("a11");      // 满了再加就会报异常
//        strs.offer("a11");    // 满了再加也不会有任何问题，返回false表示未加成功
//        strs.offer("a11", 1, TimeUnit.SECONDS); // 一段时间之内加不进去，就不再加

        System.out.println(strs);
    }
}
