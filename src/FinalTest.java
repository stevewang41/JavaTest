/**
 * Created by wangshiyi on 17/8/29.
 */

public class FinalTest {

    public static final Dog dog = new Dog(5, "小强");

    public static void main(String[] args) {
//        dog = new Dog(10, "大壮");    // 不可以将引用重新赋值
        dog.age = 23;          // 引用所指对象的属性可以重新赋值

    }
}

class Dog {

    int age;
    String name;

    public Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
