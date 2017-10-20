package thread.producerconsumer.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 利用阻塞队列实现生产者消费者模型
 * LinkedBlockingQueue —— 无界阻塞队列
 */

public class T05_LinkedBlockingQueue {

    static BlockingQueue<String> strs = new LinkedBlockingQueue<>(); // 无界阻塞队列

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {      // 1个生产者线程
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i); // 如果满了，就会阻塞
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {   // 5个消费者线程
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName()
                                + " take -"
                                + strs.take()); // 如果空了，就会阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();

        }
    }
}