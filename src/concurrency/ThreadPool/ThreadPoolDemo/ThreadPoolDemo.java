package concurrency.ThreadPool.ThreadPoolDemo;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolDemo implements ThreadPool{
    //工作线程数
    private static final int WORK_NUM=10;
    //最大线程数
    private static final int MAX_NUM=15;
    //最小线程数
    private static final int MIN_NUM=1;
    //
    @Override
    public void execute() {

    }

    @Override
    public void addWork(int num) {

    }

    @Override
    public void removeWork(int num) {

    }

    @Override
    public int getWorkSize() {
        return 0;
    }

    @Override
    public void shutdown() {

    }
}
