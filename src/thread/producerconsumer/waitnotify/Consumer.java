package thread.producerconsumer.waitnotify;

/**
 * Created by wangshiyi on 17/8/21.
 */

public class Consumer implements Runnable {

    SyncStack<Product> stack;

    public Consumer(SyncStack<Product> stack) {
        this.stack = stack;
    }


    @Override
    public void run() {
        for (int i = 0; i < 2 * SyncStack.INIT_SIZE; i++) {
            Product product = stack.pop();
            System.out.println("consume -> " + product.id);
            try {
                Thread.sleep((long) (Math.random() * 1000)); // 每生产一个产品，睡眠小于1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

