package concurrency.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    static CyclicBarrier cb=new CyclicBarrier(2,new A());
    //在到达屏障时，优先执行A
    //CyclicBarrier的计数器可以使用reset（）来进行重置，CountDownLactch不可以进行重置。
    static int a=1;
    static class A implements Runnable{
        public void run(){
            System.out.println(3);
        }
    }
    public static void main(String[] args) {
        ExecutorService exec=Executors.newCachedThreadPool();
        for(int i=0;i<2;i++)
            exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(a++);
            }
        });

        exec.shutdown();
    }
}
