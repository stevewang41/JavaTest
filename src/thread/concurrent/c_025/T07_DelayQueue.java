package thread.concurrent.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 *
 * 延时队列DelayQueue也是一个无界的BlockingQueue，用于放置实现了Delayed接口的对象
 * 其中的对象只能在延迟期满时才能从队列中取走，该队列的头部是延迟期满后保存时间最长的Delayed元素
 * 延时队列通常用来执行定时任务
 */

public class T07_DelayQueue {

    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static Random r = new Random();

    static class MyTask implements Delayed {
        long runningTime;

        MyTask(long rt) {
            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return "" + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask(now + 1000); // 1s后执行
        MyTask t2 = new MyTask(now + 2000); // 2s后执行
        MyTask t3 = new MyTask(now + 1500); // 1.5s后执行
        MyTask t4 = new MyTask(now + 2500); // 2.5s后执行
        MyTask t5 = new MyTask(now + 500);  // 0.5s后执行

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
