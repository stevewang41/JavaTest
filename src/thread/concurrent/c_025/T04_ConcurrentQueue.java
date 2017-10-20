package thread.concurrent.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wangshiyi on 17/8/24.
 */

public class T04_ConcurrentQueue {

    public static void main(String[] args) {

        Queue<String> strs = new ConcurrentLinkedQueue<>(); // 并发队列，内部加了锁

        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);            // 进队
        }

        System.out.println(strs);
        System.out.println(strs.size());

        System.out.println(strs.poll());    // 出队
        System.out.println(strs.size());

        System.out.println(strs.peek());    // 取对首，但不出队
        System.out.println(strs.size());

        // 双端队列Deque
    }
}