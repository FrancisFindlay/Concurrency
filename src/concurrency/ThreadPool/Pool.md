# 1.线程池的五种状态
    Running
    Shutdown：如果队列有任务，将队列中任务执行完毕再结束，但不会接收新任务。
    stop:shutdownNow就进入这个状态,shutdownNow不执行队列任务，也不接收新任务。对当前线程尝试中断。
    tidying:
    terminated:
    
# 2.线程池的状态记录方法
    使用一个Integer的高三位进行记录，低29位记录线程数。
    Running=   -1<<29 =111 000...000
    shutdown=   0<<29= 000 000...000
    stop=       1<<29= 001 000...000
    tidying=    2<<29= 010 000...000
    terminated= 3<<29= 100 000...000
    
    首先，取c值，进行判断是否小于coreCount，小于，调用addWorker（），
    然后，判断状态，创建Worker类，在Worker中传进来工作线程，在Worker中调用run（），
    用run调用外部方法runWorker，然后在runWorker中调用当前工作线程的run。
    
# 3.四种饱和策略
    1.AbortPolicy：直接抛出异常，默认
    2.CallerRunsPolicy:调用者所在的线程来运行任务
    3.DiscardOldestPolicy：丢弃队列中的头任务，并执行当前任务
    4.DiscardPolicy：丢弃当前任务

# 4.线程池的监控
    1.taskCount:线程池需要执行的任务数量
    2.completedTaskCount：已经完成的任务数量
    3.getPoolSize:线程池的线程数
    4.getActiveCount：活动的线程数    