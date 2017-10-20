package thread.concurrent.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshiyi on 17/8/22.
 * <p>
 * volatile和synchronized区别？
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题
 * 也就是说volatile不能替代synchronized，它只能保证可见性，并不保证原子性
 * 而synchronized既能保证可见性，又能保证原子性，但它的效率较低
 * 运行下面的程序，并分析结果
 */

public class T {

    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
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