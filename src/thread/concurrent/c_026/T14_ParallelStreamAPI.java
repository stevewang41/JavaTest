package thread.concurrent.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangshiyi on 17/8/24.
 */

public class T14_ParallelStreamAPI {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {   // 生成10000个随机数，判断每一个数是否为质数
            nums.add(1000000 + r.nextInt(1000000)); // 每个随机数的大小[1000000, 2000000]
        }


        // 第一种：使用单线程
        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        // 第二种：parallel stream api，默认使用多线程
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 判断num是否为质数
     *
     * @param num
     * @return
     */
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
