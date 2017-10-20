package thread.concurrent.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * ReentrantLock可以替代synchronized，它比synchronized更灵活
 * 需要注意的是，ReentrantLock必须要手动释放锁
 *
 * 使用synchronized的话如果执行结束或者遇到异常，JVM会自动释放锁
 * 但是ReentrantLock必须手动释放锁，因此经常在finally中进行锁的释放
 */

public class TestReentrantLock2 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock(); // 代替 synchronized(this)
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // 必须要手动释放锁
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2 ...");
        lock.unlock();
    }

    public static void main(String[] args) {
        TestReentrantLock2 t = new TestReentrantLock2();
        new Thread(t::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }
}
