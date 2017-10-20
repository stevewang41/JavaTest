package reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangshiyi on 17/6/15.
 * <p>
 * 读取配置文件，通过反射生成并操作对象（Spring框架IoC的秘密）
 */

public class ObjectPoolFactory {

    private Map<String, Object> objectPool = new HashMap<>();   // 定义一个对象池，前面是对象名，后面是实际对象
    private Properties config = new Properties();   // 属性文件

    public void init(String fileName) {     // 从指定属性文件中初始化Properties对象
        try (FileInputStream fis = new FileInputStream(fileName)) {
            config.load(fis);
        } catch (IOException ex) {
            System.out.println("读取" + fileName + "异常");
        }
    }

    private Object createObject(String clazzName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName(clazzName);  // 根据字符串来获取对应的Class对象
        return clazz.newInstance();                 // 调用默认构造器创建实例
    }

    // 根据配置文件来创建对象
    public void initObjectPool() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (String key : config.stringPropertyNames()) {
            // 每取出一对key-value，如果key中不包含百分号（%）,就调用createObject创建对象，并将对象添加到对象池中
            if (!key.contains("%")) {
                objectPool.put(key, createObject(config.getProperty(key)));
            }
        }
    }

    public Object getObject(String name) {  // 从objectPool中取出指定name对应的对象
        return objectPool.get(name);
    }

    // 根据配置文件来调用指定对象的setter方法
    public void initProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (String key : config.stringPropertyNames()) {
            // 每取出一对key-value，如果key中包含百分号（%），就调用对象的setter方法设置值，%前半为对象名字，后半控制setter方法名
            if (key.contains("%")) {
                String[] objAndProp = key.split("%");    // 将配置文件中key按%分割
                Object obj = getObject(objAndProp[0]);   // 取出调用setter方法的对象
                // 获取setter方法名:set + "首字母大写" + 剩下部分
                String methodName = "set" + objAndProp[1].substring(0, 1).toUpperCase() + objAndProp[1].substring(1);
                Class<?> clazz = obj.getClass();    // 获取对应的Class对象
                Method method = clazz.getMethod(methodName, String.class);  // 获取希望调用的setter方法
                // 通过Method的invoke方法执行setter方法，将config.getProperty(name)的值作为调用setter的方法的参数
                method.invoke(obj, config.getProperty(key));
            }
        }
    }


    public static void main(String[] args) throws Exception {

        ObjectPoolFactory opf = new ObjectPoolFactory();
        opf.init("extObj.txt");
        opf.initObjectPool();
        opf.initProperty();
        System.out.println(opf.getObject("a"));
    }
}
