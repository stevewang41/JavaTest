package thread.concurrent.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程的interrupt方法做出响应，
 * 在一个线程等待锁的过程中，可以被打断
 */

public class TestReentrantLock4 {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        final boolean[] hasLock = {false};


        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
//                lock.lock();    // 只锁定，拿不到锁就一直等待，不响应中断
                lock.lockInterruptibly(); // 可以对t2.interrupt()方法做出响应，打断它因拿不到锁的等待
                hasLock[0] = true;
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            } finally {
                if (hasLock[0]) {
                    lock.unlock();
                }
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); // 打断线程2中未拿到锁的等待

    }
}