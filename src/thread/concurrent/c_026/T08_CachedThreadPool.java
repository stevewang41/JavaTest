package thread.concurrent.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 *
 * 第二种线程池：CachedThreadPool，弹性的线程池，全部是非核心线程且数量不限制，闲置60s自动销毁
 */

public class T08_CachedThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++) {
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

        TimeUnit.SECONDS.sleep(80); // CachedThreadPool中的线程空闲超过60s自动销毁

        System.out.println(service);
    }
}
