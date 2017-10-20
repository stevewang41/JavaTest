package thread.concurrent.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangshiyi on 17/8/24.
 *
 * 第三种线程池：SingleThreadPool，只有一个核心线程
 *
 * 这种线程池可以保证任务的执行顺序，先扔的先执行
 */

public class T09_SingleThreadPool {

    public static void main(String[] args) {

        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> {
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

    }
}