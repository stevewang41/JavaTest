package thread.concurrent.c_006;

/**
 * Created by wangshiyi on 17/8/22.
 * <p>
 * 对比上面一个小程序，分析一下这个程序的输出
 * 这里为run方法加上了synchronized关键字
 *
 * @author mashibing
 */

public class T implements Runnable {

    private int count = 10;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }

}
