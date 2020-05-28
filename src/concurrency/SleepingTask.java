package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class SleepingTask extends LiftOff{
    public void run(){
        try{
            while(countDown-->0){
                System.out.println(status());
                sleep(100);
            }
        }catch (InterruptedException e){
            System.err.println("Interupted;");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
}
