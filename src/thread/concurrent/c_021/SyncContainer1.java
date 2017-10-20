package thread.concurrent.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 第一种：使用wait和notify/notifyAll来实现
 */

public class SyncContainer1<T> {

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; // 最多10个元素
    private int count = 0;      // 元素的个数


    public synchronized void put(T t) {
        while (lists.size() == MAX) { // 为什么用while而不是用if ？
            try {
                wait();//《effective java》：wait多数情况下都和while结合使用，使线程收到notify被唤醒，等到重新拿到锁继续执行时，也要重新检查一遍
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        notifyAll(); // 通知消费者线程进行消费（不精确），这里使用notifyAll而不是notify是为了防止叫醒的又是一个生产者进程
    }

    public synchronized T get() {
        while (lists.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = lists.removeFirst();
        count--;
        notifyAll(); // 通知生产者线程进行生产（不精确），《effective java》：永远使用notifyAll而不是notify
        return t;
    }

    public static void main(String[] args) {
        SyncContainer1<String> container = new SyncContainer1<>();
        for (int i = 0; i < 10; i++) {  // 启动10个消费者线程
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }, "container" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {    // 启动2个生产者线程
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "container" + i).start();
        }
    }
}
