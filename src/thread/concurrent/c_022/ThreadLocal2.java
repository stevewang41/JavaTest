package thread.concurrent.c_022;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 线程局部变量ThreadLocal
 * <p>
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 * ThreadLocal可能导致内存泄露，自己百度~
 * <p>
 * 运行下面的程序，理解ThreadLocal
 */

public class ThreadLocal2 {

    // static volatile Person p = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl.get()); // null，在第一个线程从ThreadLocal中取不到第二个线程中放置的Person对象
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());   // 在第二个线程向ThreadLocal中放置一个Person对象
        }).start();
    }

    static class Person {
        String name = "ZhangSan";
    }
}
