package thread;

/**
 * Created by wangshiyi on 17/8/21.
 * <p>
 * 模拟线程死锁，这里用 一个线程类 + flag 模拟两个不同的线程类
 */

public class ThreadDeadLock implements Runnable {

    public int flag;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        ThreadDeadLock td1 = new ThreadDeadLock();
        ThreadDeadLock td2 = new ThreadDeadLock();
        td1.flag = 1;
        td2.flag = 2;
        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);
        t1.start();
        t2.start();
    }


    @Override
    public void run() {
        System.out.println("flag=" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("t1 finish");
                }
            }
        } else if (flag == 2) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("t2 finish");
                }
            }
        }
    }
}
