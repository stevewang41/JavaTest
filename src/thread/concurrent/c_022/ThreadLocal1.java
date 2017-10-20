package thread.concurrent.c_022;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 未使用ThreadLocal
 */

public class ThreadLocal1 {

    static volatile Person p = new Person();    // 使用线程可见volatile

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "LiSi";
        }).start();
    }
}

class Person {
    String name = "ZhangSan";
}
