package concurrency.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool{
    static ThreadPoolExecutor.AbortPolicy p;
    static ThreadPoolExecutor pool=new ThreadPoolExecutor(5,
            8,
            100,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10),
            p);

}