package thread.concurrent.c_026;

import java.util.concurrent.Executor;

/**
 * Created by wangshiyi on 17/8/24.
 * <p>
 * 认识执行器Executor，它是一个接口，内部有一个抽象方法execute，用于执行任务
 */

public class T01_MyExecutor implements Executor {

    public static void main(String[] args) {
        new T01_MyExecutor().execute(() -> System.out.println("hello executor"));
    }

    @Override
    public void execute(Runnable command) {
        //new Thread(command).run();
        command.run();
    }
}