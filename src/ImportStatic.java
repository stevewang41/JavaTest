import static java.lang.Math.PI;

/**
 * Created by wangshiyi on 17/4/6.
 *
 * 静态导入
 */

public class ImportStatic {

    public static void main(String[] args) {

    }

    // 计算圆面积
    public static double calCircleArea(double r) {
        return PI * r * r;  // 静态变量Math.PI是静态导入的，所以无需给出类名
    }

    // 计算球面积
    public static double calBallArea(double r) {
        return 4 * PI * r * r;   // 静态变量Math.PI是静态导入的，所以无需给出类名
    }
}
