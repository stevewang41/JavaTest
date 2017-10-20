package annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshiyi on 17/6/10.
 */

@TestAnnotation(name = "wangshiyi")
public class AnnotationDemo {

    public static void main(String[] args) {
        Fruit fruit = new Apple();
        fruit.info();

        // 抑制编译器警告
        @SuppressWarnings(value = "unchecked")
        List<String> list = new ArrayList();
    }
}

// 标记已过时
@Deprecated
class Fruit {

    public void info() {
        System.out.println("水果的info方法...");
    }
}

class Apple extends Fruit {

    @Override   // 限定重写父类方法
    public void info() {
        System.out.println("苹果重写水果的info方法...");
    }

}

// 限定函数式接口
@FunctionalInterface
interface MyInterface {
    void onlyOneAbstractMethod(String arg);
}

// 自定义注解
@interface TestAnnotation {

    String name();      // 注解中的成员变量以无参方法的形式来声明，使用@TestAnnotation时必须为成员变量name赋值

    int age() default 25;// 如果用default为成员变量指定了默认值，使用@TestAnnotation时可以不为成员变量age赋值
}
