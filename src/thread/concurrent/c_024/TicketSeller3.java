package thread.concurrent.c_024;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 */

public class TicketSeller3 {

    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票 编号：" + i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {    // 加互斥锁，不会出问题，但效率不高
                        if (tickets.size() <= 0) {
                            break;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
