package thread.concurrent.c_012;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshiyi on 17/8/22.
 *
 *
 * 要想并发程序正确地执行，必须要保证原子性、可见性以及有序性。只要有一个没有被保证，就有可能会导致程序运行不正确。
 *
 * 一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
 *
 * 1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（强制将修改的值立即写入主存，保证内存可见性）
 *
 * 2）禁止进行指令重排序。（该指令指的是操作被volatile修饰的变量的指令，指令重排序不会影响单个线程的执行结果，但是会影响到线程并发执行的正确性）
 *
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 *
 * 使用volatile，当堆内存中的running被改变时，将会强制所有线程都去堆内存中重新读取running的值
 *
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题
 * 也就是说volatile不能替代synchronized，虽然它比synchronized效率高
 * volatile只能保证可见性和一定程度的有序性
 * 而synchronized能保证原子性、可见性以及有序性
 *
 * volatile的实现原理：
 *
 * “观察加入volatile关键字和没有加入volatile关键字时所生成的汇编代码发现，加入volatile关键字时，会多出一个lock前缀指令”
 *
 * lock前缀指令实际上相当于一个内存屏障（也成内存栅栏），内存屏障会提供3个功能：
 *
 * 1）它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；
 *
 * 2）它会强制将对缓存的修改操作立即写入主存；
 *
 * 3）如果是写操作，它会导致其他CPU中对应的缓存行无效。
 */

public class T {
    volatile boolean running = true; // 对比一下有无volatile的情况下，整个程序运行结果的区别
    void m() {
        System.out.println("m start");
        while(running) {

        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}


