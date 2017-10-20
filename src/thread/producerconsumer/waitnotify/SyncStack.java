package thread.producerconsumer.waitnotify;

/**
 * Created by wangshiyi on 17/8/21.
 * <p>
 * 栈，支持泛型，加入线程同步，但不能扩容（生产者消费者模型）
 */

public class SyncStack<E> {

    public static final int INIT_SIZE = 10;

    private Object[] stack; // Java不支持泛型数组，可使用Java提供的容器，但本题并不允许使用Java提供的容器类

    private int index;      // 栈顶索引


    /**
     * 构造方法，使用默认大小
     */
    public SyncStack() {
        stack = new Object[INIT_SIZE];
        index = -1;
    }

    /**
     * 出栈操作
     *
     * @return 栈顶对象
     */
    public synchronized E pop() {
        while (isEmpty()) { // 用while而不用if，收到notify后也要再检查一遍
            try {
                wait();     // 没有产品了，释放对象锁，让消费者线程等待生产者线程的notify
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        E top = (E) stack[index];
        stack[index--] = null;  // 将要弹出的元素置空，避免内存泄露
        notifyAll();    // 消费者线程消费完，通知因栈满而wait的生产者线程可以继续生产了
        // 通常使用notifyAll而不是notify
        return top;
    }

    /**
     * 入栈操作
     *
     * @param obj 等待入栈的对象
     */
    public synchronized void push(E obj) {
        while (isFull()) {  // 用while而不用if，收到notify后也要再检查一遍
            try {
                wait();     // 产品已满，释放对象锁，让生产者线程等待消费者线程的notify
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stack[++index] = obj;
        notifyAll();   // 生产者线程生产完，通知因栈空而wait的消费者线程可以继续消费了
    }

    /**
     * 查看栈是否为空
     *
     * @return 如果栈为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return index == -1;
    }

    /**
     * 查看栈是否满
     *
     * @return 如果栈满返回true, 否则返回false
     */
    public boolean isFull() {
        return index >= stack.length - 1;
    }

}