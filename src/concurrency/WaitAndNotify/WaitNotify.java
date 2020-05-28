package concurrency.WaitAndNotify;

import java.util.concurrent.ThreadPoolExecutor;

/*
    1.wait notify notifyAll 需要先对对象加锁
    2.调用wait后，线程状态转换为waiting，并且加入阻塞队列
    3.notify 后，wait不会立即释放，而是等锁释放后，等待线程才有机会释放
    4.notify后，所有正在wait的线程由waiting转为blocking
 */
public class WaitNotify {
    static boolean flag=true;
    static Object object=new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread wait=new Thread(new Wait(),"wait");
        Thread notify=new Thread(new Notify(),"notify");
        wait.start();
        Thread.sleep(1000);
        notify.start();

    }

    static class Wait implements Runnable{
        public void run(){
            synchronized (object){
                while(flag==true){
                    try {
                        System.out.println("wait");
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class Notify implements Runnable{
        public void run(){
            synchronized (object){
                flag=false;
                object.notifyAll();
                System.out.println("notify");
            }

        }
    }
}