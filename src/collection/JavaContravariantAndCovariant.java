package collection;

import java.util.ArrayList;

/**
 * Created by wangshiyi on 17/4/14.
 *
 * Java逆变和协变
 */

public class JavaContravariantAndCovariant {

    public static void main(String[] args) {

        Number number = new Integer(0);     // Integer是Number的子类

        Number[] numbers = new Integer[3];  // Integer[]也是Number[]的子类，所以数组是协变的

//        ArrayList<Number> list1 = new ArrayList<Integer>(); // ArrayList<Integer>不是ArrayList<Number>的子类，泛型不是协变的
//
//        ArrayList<Integer> list2 = new ArrayList<Number>(); // ArrayList<Number>不是ArrayList<Integer>的子类，泛型也不是逆变的

        ArrayList<? extends Number> list3 = new ArrayList<Integer>();  // <? extends>实现了泛型的协变

        ArrayList<? super Number> list4 = new ArrayList<Object>();     // <? super>实现了泛型的逆变

        ArrayList<? extends Number> list5 = new ArrayList<Integer>();
//        list5.add(new Integer(1));  // ? extends Number包含Integer、Float
//        list5.add(new Float(1.2f)); // 但没特指为Integer还是Float，所以报错

        ArrayList<? super Number> list6 = new ArrayList<Object>();
        list6.add(new Integer(1));  // ? super Number包含Number和Number基类中的某一类型
        list6.add(new Float(1.2f)); // Integer，Float必定为这某一类型的子类，所以不会报错
    }
}
