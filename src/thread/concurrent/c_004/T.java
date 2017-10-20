package thread.concurrent.c_004;

/**
 * Created by wangshiyi on 17/8/22.
 * <p>
 * synchronized关键字
 * 对某个对象加锁
 *
 * @author mashibing
 */

public class T {

    private static int count = 10;

    public synchronized static void m() { // 这里等同于synchronized(T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized (T.class) { // 考虑一下这里写synchronized(this)是否可以？ 当然不可以
            count--;
        }
    }

}
