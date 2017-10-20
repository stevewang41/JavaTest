/**
 * Created by wangshiyi on 17/7/31.
 */

public class StringTest {

    public static void main(String[] args) {

        String str1 = "HelloWorld";
        String str2 = "HelloWorld";
        System.out.println(str1 == str2);   // true

        String str3 = new String("HelloWorld");
        String str4 = new String("HelloWorld");
        System.out.println(str3 == str4);   // false
        System.out.println(str3 == str1);   // false

        String str5 = "Hello" + "World";    // 编译期可以确定，JVM将常量字符串优化为连接后的值
        System.out.println(str5 == str1);   // true

        String str6 = "Hello" + new String("World");  // 编译期无法确定
        System.out.println(str6 == str1);   // false

        String str7 = "World";
        String str8 = "Hello" + str7;       // 编译期无法确定
        System.out.println(str8 == str1);   // false

        final String str9 = "World";        // final修饰的变量，它在编译时被解析为常量值的一个本地拷贝
        String str10 = "Hello" + str9;      // 编译期可以确定，JVM将常量字符串优化为连接后的值
        System.out.println(str10 == str1);  // true

        final String str11 = getWorldStr(); // 虽然用final修饰，但是其赋值是通过方法调用返回的，只能在运行时确定
        String str12 = "Hello" + str11;     // 编译期无法确定
        System.out.println(str12 == str1);  // false

    }

    private static String getWorldStr() {
        return "World";

    }
}