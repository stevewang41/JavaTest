package thread.concurrent.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshiyi on 17/8/23.
 * <p>
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 第二种：使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 */

public class SyncContainer2<T> {

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; // 最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();   // 生产者条件
    private Condition consumer = lock.newCondition();   // 消费者条件

    public void put(T t) {
        try {
            lock.lock();
            while (lists.size() == MAX) { // 想想为什么用while而不是用if？
                producer.await();
            }
            lists.add(t);
            count++;
            consumer.signalAll();   // 通知所有在consumer上await的线程，即精确通知所有消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();   // 通知所有在producer上await的线程，即精确通知所有生产者线程进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        SyncContainer2<String> c = new SyncContainer2<>();

        for (int i = 0; i < 10; i++) {  // 启动10个消费者线程
            new Thread(() -> {
                for (int j = 0; j < 5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {   // 启动2个生产者线程
            new Thread(() -> {
                for (int j = 0; j < 25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}

