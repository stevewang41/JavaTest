package thread.concurrent.c_026;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * Future是一个接口，ExecutorService中submit()的返回值类型就是Future —— 未来的返回值
 */

public class T06_Future {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(500);
                return 1000;
            }
        });

        new Thread(task).start();   // 新建一个线程执行task

        System.out.println(task.get()); // 阻塞直到task完成，然后返回call()的返回值


        // *******************************

        ExecutorService service = Executors.newFixedThreadPool(5);  // 新建一个拥有5个线程的线程池
        Future<Integer> f = service.submit(new Callable<Integer>() {// 让线程池执行任务
            @Override
            public Integer call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(500);
                return 1;
            }
        });
        System.out.println(f.get());    // 阻塞直到task完成，然后返回call()的返回值
        System.out.println(f.isDone());
    }
}

