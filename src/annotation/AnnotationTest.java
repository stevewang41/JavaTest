package annotation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangshiyi on 17/6/12.
 *
 * 通过元数据注解ActionListenerFor来为程序中的按钮绑定事件监听器
 */

public class AnnotationTest {

    private JFrame mainWin = new JFrame("使用注解绑定事件监听器");

    @ActionListenerFor(listener = ConfirmListener.class)    // 为btnConfirm绑定事件监听器
    private JButton btnConfirm = new JButton("确定");

    @ActionListenerFor(listener = CancelListener.class)     // 为btnCancel绑定事件监听器
    private JButton btnCancel = new JButton("取消");

    public void init() {
        JPanel jp = new JPanel();
        jp.add(btnConfirm);
        jp.add(btnCancel);
        mainWin.add(jp);

        ActionListenerInstaller.processAnnotations(this);   // 用注解处理工具处理该类所有的成员变量

        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public static void main(String[] args){
        new AnnotationTest().init();
    }

}


class ConfirmListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null, "单击了确定按钮");
    }
}

class CancelListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null, "单击了取消按钮");
    }
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ActionListenerFor {

    // 该成员变量用于保存监听器的实现类
    Class<? extends ActionListener> listener();
}
