package thread.concurrent.c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 第六种线程池：ForkJoinPool，主要用于并行计算，Java7新增
 * WorkStealingPool本质上是使用ForkJoinPool来实现的
 */

public class T12_ForkJoinPool {

    static int[] nums = new int[1000000];   // 求1000000个数的和
    static final int MAX_NUM = 50000;       // 每一个小任务计算量的上限
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);   // 每个数都是100以内的随机值
        }
        System.out.println(Arrays.stream(nums).sum()); // 第一种：使用Stream API计算
    }


    /**
     * 使用继承自RecursiveAction的ForkJoinTask，它没有返回值，无法汇总子任务的计算结果
     */
//    static class AddAction extends RecursiveAction {
//
//        int start, end;
//
//        AddAction(int start, int end) {
//            this.start = start;
//            this.end = end;
//        }
//
//        @Override
//        protected void compute() {
//            if (end - start <= MAX_NUM) {
//                long sum = 0L;
//                for (int i = start; i < end; i++) {
//                    sum += nums[i];
//                }
//                System.out.println("from:" + start + " to:" + end + " = " + sum);
//            } else {    // 子任务大小超出上限，需要分一下
//                int middle = start + (end - start) / 2;
//                AddAction subTask1 = new AddAction(start, middle);
//                AddAction subTask2 = new AddAction(middle, end);
//                subTask1.fork();    // 执行子任务
//                subTask2.fork();
//            }
//        }
//    }

    /**
     * 第二种：使用继承自RecursiveTask的ForkJoinTask，有返回值，最后可以汇总子任务的计算结果
     */
    static class AddTask extends RecursiveTask<Long> {

        int start, end;

        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) sum += nums[i];
                return sum;
            }
            int middle = start + (end - start) / 2;
            AddTask subTask1 = new AddTask(start, middle);
            AddTask subTask2 = new AddTask(middle, end);
            subTask1.fork();
            subTask2.fork();
            return subTask1.join() + subTask2.join();   // 合并子任务的计算结果
        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        fjp.execute(task);
        long result = task.join();
        System.out.println(result);

        System.in.read();
    }
}
