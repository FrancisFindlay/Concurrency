package concurrency.ThreadPool.ThreadPoolDemo;

public interface ThreadPool {
    //执行一个线程
    public void execute();
    //增加一个工作线程
    public void addWork(int num);
    //减少一个工作线程
    public void removeWork(int num);
    //得到正在等待的线程数量
    public int getWorkSize();
    //关闭线程池
    public void shutdown();
}
