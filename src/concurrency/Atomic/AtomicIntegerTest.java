package concurrency.Atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable{
    private AtomicInteger i=new AtomicInteger(0);
    public int getValue(){
        return i.get();
    }
    private void evenIncrement(){i.addAndGet(2);}
    public void run(){
        while(true){
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("aborting");
                System.exit(0);
            }
        },5000);
        ExecutorService exec=Executors.newCachedThreadPool();
        AtomicIntegerTest air=new AtomicIntegerTest();
        exec.execute(air);
        while(true){
            int val=air.getValue();
            if(val%2!=0){
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
