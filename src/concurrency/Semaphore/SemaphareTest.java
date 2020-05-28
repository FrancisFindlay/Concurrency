package concurrency.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphareTest {
    private static final int COUNT=30;
    private static ExecutorService exec=Executors.newFixedThreadPool(COUNT);
    private static Semaphore s=new Semaphore(10);

    public static void main(String[] args) {
        for(int i=0;i<COUNT;i++){
            exec.execute(new Runnable(){
                @Override
                public void run() {
                    try {
                        s.tryAcquire();
                        System.out.println("save data");
                        s.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        exec.shutdownNow();
    }
}
