package gc;

import java.lang.ref.WeakReference;

/**
 * Created by wangshiyi on 17/8/29.
 *
 * 弱引用
 *
 * 如果在WeakReference创建时，传入了一个ReferenceQueue对象
 * 当被WeakReference引用的对象的生命周期结束，一旦被GC检查到，GC将会把该对象添加到ReferenceQueue中，待ReferenceQueue处理。
 * 当GC过后对象一直不被加入ReferenceQueue，它可能存在内存泄漏。
 */

public class WeakReferenceTest {

    public static void main(String[] args) {

        String str = new String("疯狂Java讲义");
        WeakReference<String> ref = new WeakReference<>(str); // 创建一个弱引用，让此弱引用引用到"疯狂Java讲义"字符串(可以在第二个参数中传入引用队列)
        str = null;     // 切断指向"疯狂Java讲义"字符串的强引用
        System.out.println(ref.get());    // 取出弱引用所引用的对象
        System.gc();    // 建议垃圾回收
        System.runFinalization();
        System.out.println(ref.get());    // 再次取出弱引用所引用的对象
    }
}
