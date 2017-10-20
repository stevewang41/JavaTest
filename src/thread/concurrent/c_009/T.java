package thread.concurrent.c_009;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/22.
 *
 * 一个同步方法是否可以调用另外一个同步方法？
 * 当然可以，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * @author mashibing
 */

public class T {

    synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();  // 调用m2，当前线程会再次申请到当前对象的锁
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
}
