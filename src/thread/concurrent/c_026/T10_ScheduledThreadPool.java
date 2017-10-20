package thread.concurrent.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 第四种线程池：ScheduledThreadPool，用于执行定时任务，核心线程数固定，非核心线程数不限制且闲置即销毁
 */

public class T10_ScheduledThreadPool {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);  // 立即执行，每隔500ms重复执行
    }
}