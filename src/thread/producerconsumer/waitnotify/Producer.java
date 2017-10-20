package thread.producerconsumer.waitnotify;

/**
 * Created by wangshiyi on 17/8/21.
 * <p>
 * 生产者
 */

public class Producer implements Runnable {

    SyncStack<Product> stack;

    public Producer(SyncStack<Product> stack) {
        this.stack = stack;
    }


    @Override
    public void run() {
        for (int i = 0; i < 2 * SyncStack.INIT_SIZE; i++) {
            Product product = new Product(i);
            stack.push(product);
            System.out.println("produce -> " + product.id);
            try {
                Thread.sleep((long) (Math.random() * 2)); // 每生产一个产品，睡眠小于2毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}