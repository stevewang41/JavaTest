package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshiyi on 17/6/10.
 */

public class ListAdd {

    public static void main(String[] args) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(1);
        path.add(2);
        path.add(3);
//        result.add(path);                   // 浅度克隆path
        result.add(new ArrayList<>(path));    // 深度克隆path
        System.out.println(result);
        path.add(4);
        System.out.println(result);

    }
}
