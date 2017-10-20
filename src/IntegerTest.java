/**
 * Created by wangshiyi on 17/7/31.
 *
 * http://blog.csdn.net/sujz12345/article/details/53026880
 */

public class IntegerTest {

    public static void main(String[] args) {

        Integer i1 = 127;   // autoboxing
        Integer i2 = 127;   // autoboxing
        System.out.println(i1.equals(i2));   // true
        System.out.println(i1 == i2);        // true

        Integer i3 = 128;   // autoboxing
        Integer i4 = 128;   // autoboxing
        System.out.println(i3.equals(i4));   // true
        System.out.println(i3 == i4);        // false

        Integer i5 = new Integer(127);
        Integer i6 = new Integer(127);
        System.out.println(i5.equals(i6));  // true
        System.out.println(i5 == i6);       // false

        Integer i7 = 127;   // autoboxing
        Integer i8 = new Integer(127);
        System.out.println(i7.equals(i8));  // true
        System.out.println(i7 == i8);       // false

        int i = 127;
        System.out.println(i7.equals(i));  // true
        System.out.println(i8.equals(i));  // true
        System.out.println(i7 == i);       // true
        System.out.println(i8 == i);       // true
    }
}