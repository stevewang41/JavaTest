package thread.concurrent.c_023;

import java.util.Arrays;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 线程安全的单例模式：
 * <p>
 * 阅读文章：http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 更好的是采用下面的方式（静态内部类），既不用加锁，也能实现懒加载
 */

public class Singleton {

    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                Singleton.getInstance();
            });
        }

        Arrays.asList(ths).forEach(o -> o.start());
    }

}
