package thread.concurrent.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangshiyi on 17/8/24.
 *
 * Collections.synchronizedXxx()
 * 利用工厂方法将线程不安全的容器包装成线程安全的容器，也是通过加synchronized锁
 */

public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> syncList = Collections.synchronizedList(list);
    }
}

