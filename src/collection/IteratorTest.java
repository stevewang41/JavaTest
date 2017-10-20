package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangshiyi on 17/7/17.
 */

public class IteratorTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        System.out.println(list);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            it.remove();
//            list.remove(str);   // ConcurrentModificationException
//            list.remove(0);     // ConcurrentModificationException
        }
        System.out.println(list);
    }
}
