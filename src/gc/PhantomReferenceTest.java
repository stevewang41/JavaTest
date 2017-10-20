package gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by wangshiyi on 17/8/29.
 * <p>
 * 虚引用
 */

public class PhantomReferenceTest {

    public static void main(String[] args) {

        String str = new String("疯狂Java讲义");
        ReferenceQueue<String> queue = new ReferenceQueue<>();  // 创建一个引用队列，虚引用必须和引用队列一起使用
        PhantomReference<String> ref = new PhantomReference<>(str, queue); // 创建一个虚引用，让此虚引用引用到"疯狂Java讲义"字符串
        str = null;     // 切断指向"疯狂Java讲义"字符串的强引用
        System.out.println(ref.get());    // 取出虚引用所引用的对象，并不能通过虚引用获取被引用的对象，所以此处输出null
        System.gc();    // 建议垃圾回收
        System.runFinalization();
        // 垃圾回收之后，虚引用将被放入引用队列中
        // 取出引用队列中最先进入队列的引用与pr进行比较
        System.out.println(queue.poll() == ref);
    }
}