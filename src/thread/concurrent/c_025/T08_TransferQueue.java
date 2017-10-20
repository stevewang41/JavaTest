package thread.concurrent.c_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by wangshiyi on 17/8/24.
 *
 * TransferQueue继承了BlockingQueue（BlockingQueue又继承了Queue）并扩展了一些新方法
 *
 * BlockingQueue（和Queue）是Java 5中加入的接口，它是指这样的一个队列：
 * 当生产者向队列添加元素但队列已满时，生产者会被阻塞；当消费者从队列移除元素但队列为空时，消费者会被阻塞。
 *
 * TransferQueue则更进一步，生产者会一直阻塞直到所添加到队列的元素被某一个消费者所消费（不仅仅是添加到队列里就完事）
 * 新添加的transfer方法用来实现这种约束，阻塞就是发生在元素从一个线程transfer到另一个线程的过程中
 * 它有效地实现了元素在线程之间的传递（以建立Java内存模型中的happens-before关系的方式）
 */

public class T08_TransferQueue {

    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

//		new Thread(() -> {    // 先启动消费者线程，不会发生阻塞
//            try {
//				System.out.println(strs.take());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start();

        strs.transfer("aaa");   // 生产者线程直接将产品交给消费者线程进行消费，不用放到队列里
//        strs.put("aaa");

        new Thread(() -> {      // 后启动消费者线程，会发生阻塞
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

