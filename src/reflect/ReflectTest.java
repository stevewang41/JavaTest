package reflect;

/**
 * Created by wangshiyi on 17/6/23.
 */

public class ReflectTest {

    public static void main(String[] args) {

        // 第一种表达方式，实际在告诉我们任何一个类都有一个隐含的静态成员变量class
        Class c1 = ReflectTest.class;

        // 第二种表达方式，已经知道该类的对象通过getClass()
        Class c2 = new ReflectTest().getClass();

        // c1、c2都是Foo类的class type，一个类的class type只对应Class类的一个实例对象
        System.out.println(c1 == c2);	// true

        // 第三种表达方式，动态加载类，得到类的class type
        // 用new创建对象都是静态加载类，在编译时就需要加载所有可能使用到的类
        Class c3 = null;
        try {
            c3 = Class.forName("com.example.java.reflect.ReflectTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(c3.getSimpleName());
        System.out.println(c3.getName());

        System.out.println(c2 == c3);	// true

    }
}
