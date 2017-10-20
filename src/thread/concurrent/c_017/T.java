package thread.concurrent.c_017;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果将o的引用指向另外一个对象，则锁定的对象发生改变
 * 锁的信息是记录在堆内存中的，应避免将锁定对象的引用指向另外的对象
 */

public class T {

    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        // 启动第一个线程
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 创建第二个线程
        Thread t2 = new Thread(t::m, "t2");

        t.o = new Object(); // 锁对象发生改变，所以t2线程启动后可以执行，如果注释掉这句话，线程2将永远得不到执行机会

        t2.start();
    }
}
