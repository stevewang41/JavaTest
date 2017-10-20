package thread.concurrent.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 使用ConcurrentQueue提高并发性
 *
 */

public class TicketSeller4 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票 编号：" + i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String t = tickets.poll();
                    if (t == null) {
                        break;
                    } else {
                        System.out.println("销售了--" + t);
                    }
                }
            }).start();
        }
    }
}
