package thread.concurrent.c_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * ReentrantLock还可以指定为公平锁，谁等待锁的时间长，谁就优先得到锁
 * 默认（无参数）的ReentrantLock为不公平锁，即一个线程将锁释放后，CPU从等待锁的线程随机选一个得到这把锁
 * 公平锁的效率低于不公平锁
 */

public class TestReentrantLock5 implements Runnable {

    private static ReentrantLock lock = new ReentrantLock(true); // 参数为true表示为公平锁，请对比输出结果

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();  // 每打印完一次都释放锁，如果是公平锁，再拿到锁的必定是另一个线程
            }
        }
    }

    public static void main(String[] args) {
        TestReentrantLock5 t = new TestReentrantLock5();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
    }
}
