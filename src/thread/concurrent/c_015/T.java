package thread.concurrent.c_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 解决同样的问题比synchronized更高效的方法，使用AtomicXxx，它也能保证可见性和原子性，内部原理为乐观锁CAS
 * AtomicXxx类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */

public class T {
//    volatile int count = 0;

    AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
//            if (count.get() < 1000) {  // 如果加上，不能保证与下面的原子方法的连续调用是原子性的
            count.incrementAndGet(); // 原子方法，替代非原子操作count++
//            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}