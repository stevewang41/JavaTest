package reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wangshiyi on 17/6/14.
 * <p>
 * 通过反射查看类信息
 */

@SuppressWarnings(value = "unchecked")
@Deprecated
@Anno
@Anno
public class ClassTest {


    public String name;

    private int age;

    private ClassTest() {   // 私有的构造器
    }

    public ClassTest(String name) {
        System.out.println("执行有参数的构造器");
    }

    private void secret() {
        System.out.println("私有成员方法");
    }

    public void info() {
        System.out.println("执行无参数的info方法");
    }

    public void info(String str) {
        System.out.println("执行有参数的info方法" + "，其str参数值：" + str);
    }

    class Inner {   // 测试用的内部类
    }

    public static void main(String[] args) throws Exception {

        ArrayList list1 = new ArrayList();
        ArrayList<String> list2 = new ArrayList<>();
        Class c1 = list1.getClass();
        Class c2 = list2.getClass();
        System.out.println(c1 == c2);
        Method m = c2.getMethod("add", Object.class);
        m.invoke(list2, 100);
        System.out.println(list2);


        Class<ClassTest> clazz = ClassTest.class;   // 获取ClassTest类的类类型(class type)

        Field[] fs = clazz.getDeclaredFields();
        System.out.println("\nClassTest类自己声明的全部成员变量如下：");
        for (Field f : fs) {
            System.out.println(f);
        }

        Field[] pubFs = clazz.getFields();
        System.out.println("\nClassTest类的全部public成员变量（包括从父类继承而来的）如下：");
        for (Field f : pubFs) {
            System.out.println(f);
        }


        Constructor[] ctors = clazz.getDeclaredConstructors();
        System.out.println("\nClassTest类的全部构造器如下：");
        for (Constructor c : ctors) {
            System.out.println(c);
        }

        Constructor[] pubCtors = clazz.getConstructors();
        System.out.println("\nClassTest类的全部public构造器如下：");
        for (Constructor c : pubCtors) {
            System.out.println(c);
        }


        Method[] mtds = clazz.getDeclaredMethods();
        System.out.println("\nClassTest类自己声明的全部成员方法如下：");
        for (Method md : mtds) {
            System.out.println(md);
        }

        Method[] pubMtds = clazz.getMethods();
        System.out.println("\nClassTest类的全部public成员方法（包括从父类继承而来的）如下：");
        for (Method md : pubMtds) {
            System.out.println(md);
        }

        System.out.println("\nClassTest类中带一个字符串参数的info()方法为：" + clazz.getMethod("info", String.class));


        Annotation[] anns = clazz.getAnnotations();     // 获取该Class对象所对应类的上的全部注解
        System.out.println("\nClassTest的全部Annotation如下：");
        for (Annotation an : anns) {
            System.out.println(an);
        }

        System.out.println("\n该Class元素上的@SuppressWarnings注解为：" + Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));    // @Retention(RetentionPolicy.SOURCE)反射获取不到

        System.out.println("\n该Class元素上的@Anno注解为：" + Arrays.toString(clazz.getAnnotationsByType(Anno.class)));

        Class<?>[] inners = clazz.getDeclaredClasses(); // 获取该Class对象所对应类的全部内部类
        System.out.println("ClassTest的全部内部类如下：");
        for (Class c : inners) {
            System.out.println(c);
        }

        Class inClazz = Class.forName("\ncom.example.java.reflect.ClassTest$Inner");  // 使用Class.forName方法加载ClassTest的Inner内部类
        System.out.println("\ninClazz对应类的外部类为：" + inClazz.getDeclaringClass());// 通过getDeclaringClass()访问该类所在的外部类
        System.out.println("\nClassTest的包为：" + clazz.getPackage());
        System.out.println("\nClassTest的父类为：" + clazz.getSuperclass());
    }
}


@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos {
    Anno[] value();
}

@Repeatable(Annos.class)
@interface Anno {

}