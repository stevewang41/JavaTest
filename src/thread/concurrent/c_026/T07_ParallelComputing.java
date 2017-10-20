package thread.concurrent.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 并行计算
 */

public class T07_ParallelComputing {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 第一种：使用一个线程来计算
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1, 200000);    // 求[1, 200000]中所有的质数
        long end = System.currentTimeMillis();
        System.out.println(end - start);


        // 第二种：使用线程池来并行计算
        final int cpuCoreNum = 4;   // 启动的线程池大小一般不小于CPU核数
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        // 将总任务并不平均的拆分成多个子任务，因为越大的数，计算时间越长
        MyTask t1 = new MyTask(1, 80000);       // 求[1, 80000]中所有的质数
        MyTask t2 = new MyTask(80001, 130000);  // 求[80001, 130000]中所有的质数
        MyTask t3 = new MyTask(130001, 170000); // 求[130001, 170000]中所有的质数
        MyTask t4 = new MyTask(170001, 200000); // 求[170001, 200000]中所有的质数

        start = System.currentTimeMillis();
        // 把任务扔到线程池去执行
        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        // 阻塞等待任务执行结束
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static class MyTask implements Callable<List<Integer>> {

        int start, end;

        MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(start, end);
        }
    }

    /**
     * 判断num是否是质数
     *
     * @param num
     * @return
     */
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求[start, end]中所有的质数
     *
     * @param start
     * @param end
     * @return
     */
    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }
}

