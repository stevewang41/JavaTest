package thread.concurrent.c_008;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/22.
 * <p>
 * 如果仅对业务的写方法加锁，而对业务读方法不加锁
 * 容易产生脏读问题（DirtyRead）   可用CopyOnWrite解决
 */

public class Account {

    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;

        try {
			Thread.sleep(2000); // 故意睡2秒，如果此时另一个线程调用getBalance，就会读到脏数据
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {

        Account a = new Account();
        new Thread(() -> a.set("ZhangSan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("ZhangSan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("ZhangSan"));
    }
}
