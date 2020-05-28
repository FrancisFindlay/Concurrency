package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Executors.*;

public class SimplePriorities implements Runnable{
    private int countDown=5;
    private volatile double d;//确保不进行任何编译器优化 volatile
    /*
https://www.jianshu.com/p/ccfe24b63d87
保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（实现可见性）
禁止进行指令重排序。（实现有序性）
volatile 只能保证对单次读/写的原子性。i++ 这种操作不能保证原子性。

内存屏障，又称内存栅栏，是一个 CPU 指令。
在程序运行时，为了提高执行性能，编译器和处理器会对指令进行重排序，JMM 为了保证在不同的编译器和 CPU 上有相同的结果，通过插入特定类型的内存屏障来禁止特定类型的编译器重排序和处理器重排序，插入一条内存屏障会告诉编译器和 CPU：不管什么指令都不能和这条 Memory Barrier 指令重排序


     */
    private int priority;
    public SimplePriorities(int priority){
        this.priority=priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread()+":"+countDown;
    }
    public void run(){
        Thread.currentThread().setPriority(priority);
        while(true){
            for(int i=0;i<10000;i++){
                d+=(Math.PI+Math.E)/(double)i;
                if(i%1000==0){
                    Thread.yield();
                }
                System.out.println(this);
                if(countDown--==0)
                    return ;
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService exec=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
