package thread.concurrent.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by wangshiyi on 17/8/24.
 *
 * 同步队列SynchronusQueue，容量为0
 *
 */

public class T09_SynchronusQueue {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {  // 消费者线程
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");    // 内部调用的是TransferQueue的transfer，直接交给消费者消费，阻塞等待消费者消费
//        strs.add("aaa");  // SynchronusQueue无法add，Queue full
        System.out.println(strs.size());
    }
}