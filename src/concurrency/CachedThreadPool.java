package concurrency;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
