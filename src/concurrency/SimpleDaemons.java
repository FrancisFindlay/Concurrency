package concurrency;

import java.util.concurrent.TimeUnit;
//只要有任何非后台线程还在运行，程序就不会停止，main就是一个非后台;
//当所有非后台程序执行完成，程序stop；

public class SimpleDaemons implements Runnable{
    public void run(){
        try{
            while(true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread()+" "+this);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++){
            Thread daemon=new Thread(new SimpleDaemons());
            daemon.setDaemon(true);//必须在线程启动前setDaemon（），才能设置为后台程序;
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
