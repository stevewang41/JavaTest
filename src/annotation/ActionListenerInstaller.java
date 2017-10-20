package annotation;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

/**
 * Created by wangshiyi on 17/6/12.
 * <p>
 * ActionListenerFor注解处理工具
 */

public class ActionListenerInstaller {

    public static void processAnnotations(Object obj) {

        try {
            Class clazz = obj.getClass();   // 获取对象obj的类
            for (Field field : clazz.getDeclaredFields()) {    // 遍历类中声明的所有成员变量
                field.setAccessible(true);  // 将该成员变量设置成可自由访问
                ActionListenerFor annotation = field.getAnnotation(ActionListenerFor.class);    // 获取该成员变量上ActionListenerFor类型的注解
                Object fObj = field.get(obj); // 获取该成员变量的值
                if (annotation != null && fObj != null && fObj instanceof AbstractButton) {
                    Class<? extends ActionListener> listenerClazz = annotation.listener();  // 获取注解里的listener元数据
                    ActionListener actionListener = listenerClazz.newInstance();    // 使用反射来创建监听器对象
                    AbstractButton btn = (AbstractButton) fObj;
                    btn.addActionListener(actionListener);  // 为button添加事件监听器
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
