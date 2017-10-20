package thread.concurrent.c_024;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 */

public class TicketSeller1 {

    static List<String> tickets = new ArrayList<>();    // ArrayList上的remove等操作都不是线程安全的

    static {
        for (int i = 0; i < 1000; i++) {   // 1000张票
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {  // 10个线程
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}