package thread.concurrent.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 使用ReentrantLock可以进行“尝试锁定”tryLock
 * 根据tryLock的返回值可以判定拿没拿到锁，无论拿没拿到锁，方法都将继续执行，不会阻塞
 * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unlock的处理，必须放到finally中
 */

public class TestReentrantLock3 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定
     *
     */
    void m2() {

        // 第一种：尝试锁定，如果拿到了锁
//        boolean locked = lock.tryLock();
//        if (locked) {
//            System.out.println("m2 ..." + locked);
//            lock.unlock();
//        }

        // 第二种：更常用的是，在一段时间内尝试锁定
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS); // 5s内拿不到锁，才会返回false
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                System.out.println("m2 ..." + locked);
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TestReentrantLock3 t = new TestReentrantLock3();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }
}
