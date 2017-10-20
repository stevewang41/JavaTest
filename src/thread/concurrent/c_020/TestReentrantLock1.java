package thread.concurrent.c_020;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 本例中使用互斥锁synchronized，由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 */

public class TestReentrantLock1 {

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2 ...");
    }

    public static void main(String[] args) {
        TestReentrantLock1 t = new TestReentrantLock1();
        new Thread(t::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }
}
