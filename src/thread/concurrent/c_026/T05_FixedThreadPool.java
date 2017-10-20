package thread.concurrent.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 线程池的概念
 *
 * 1）避免线程的创建和销毁带来的性能开销
 * 2）避免大量的线程间因互相抢占系统资源导致的阻塞现象
 * 3）能够对线程进行简单的管理并提供定时执行、间隔执行等功能
 *
 * 第一种线程池：FixedThreadPool，固定的线程池，只有核心线程且这些核心线程不会被回收
 */

public class T05_FixedThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5);  // 创建一个有5个线程的线程池

        for (int i = 0; i < 6; i++) {   // 执行6个任务
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        service.shutdown();     // 待所有任务执行完，关闭线程池；若想立即关闭，用shutdownNow()
        System.out.println(service.isTerminated()); // 是否执行完所有任务
        System.out.println(service.isShutdown());   // 是否正在关闭Shutting down
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);  // 主线程睡5s，线程池中的任务肯定执行完了
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
