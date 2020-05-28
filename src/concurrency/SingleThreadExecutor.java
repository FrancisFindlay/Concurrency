package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    //线程数量为1，提交多个任务，这些任务都将排队使用，并且任何线程都只有唯一的任务在运行;
    public static void main(String[] args) {
        ExecutorService exec= Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
