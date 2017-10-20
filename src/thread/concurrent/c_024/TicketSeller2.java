package thread.concurrent.c_024;

import java.util.Vector;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 不能，虽然Vector的remove操作是线程安全的，但是每个线程中的判断和remove是分离的
 */

public class TicketSeller2 {

    static Vector<String> tickets = new Vector<>(); // Vector上的remove等操作是线程安全的

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票 编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}