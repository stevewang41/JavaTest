package thread.producerconsumer.waitnotify;

/**
 * Created by wangshiyi on 17/8/21.
 * <p>
 * 生产者消费者，此例只有一个生产者和一个消费者
 */

public class Client {

    public static void main(String[] args) {
        SyncStack<Product> stack = new SyncStack<>();
        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);
        new Thread(producer).start();   // 启动生产者线程
        new Thread(consumer).start();   // 启动消费者线程
    }
}