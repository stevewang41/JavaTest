package thread.concurrent.c_025;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低（每次写都要复制一遍容器），读时效率高（读的时候不用加锁了）
 * 适合写少读多的环境：如事件监听器
 */

public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        List<String> lists =
//                new ArrayList<>(); // 这个会出并发问题！
//                new Vector();
                new CopyOnWriteArrayList<>();   // 写时效率低，读时效率高
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i = 0; i < ths.length; i++) {  // 100个线程

            Runnable task = new Runnable() {    // 每个线程向List中装入1000个随机字符串
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }

        runAndComputeTime(ths);

        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}
