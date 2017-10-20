package thread.concurrent.c_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 第五种线程池：WorkStealingPool，工作窃取，线程池中的每个线程都自己维护一个工作队列
 * 当某个线程运行完自己工作队列中的所有任务后，会去窃取其他线程的工作队列中的任务（主动找活干）
 *
 * WorkStealingPool中的线程都是Daemon Thread（后台线程/守护线程）
 * 一旦主函数退出（虚拟机退出）则看不到任何输出（有可能还在运行）
 */

public class T11_WorkStealingPool {

    public static void main(String[] args) throws IOException {

        System.out.println(Runtime.getRuntime().availableProcessors()); // 输出目前CPU的核数

        ExecutorService service = Executors.newWorkStealingPool();// 如果是4核，WorkStealingPool中默认有4个线程
        service.execute(new R(1000));   // 延迟1s
        service.execute(new R(2000));   // 延迟2s
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        System.in.read();   // 阻塞主函数
    }

    static class R implements Runnable {

        int delay;

        R(int delay) {
            this.delay = delay;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(delay + " " + Thread.currentThread().getName());
        }
    }
}
