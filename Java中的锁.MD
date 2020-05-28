# Java中的锁

# 5.1 Lock 接口

  Lock接口提供的区别于同步的特性：
  
  * 非阻塞获取锁
  
  * 能被中断的获取
  
  * 超时获取
  
  trylock():非阻塞获取
  
  tryLock(Long time,TimeUnit unit):超时获取
  
# 5.2 AQS
  同步器的状态根据三个方法来获取：
  
  * getState()
  
  * setState()
  
  * compareAndSetState()：CAS
  
  同步器可以重写的方法：
  
  * tryAcquire（int arg）：独占获取同步状态，然后设置同步状态
  
  * tryRelease（int arg）：独占释放同步状态
  
  * tryAcquireShared（int arg）：共享获取，返回大于等于0的值表示获取成功，反之失败
  
  * tryReleaseShared(int arg)
  
  实现自定义控件会用到的模板方法：
  
  * void acquire（int arg）：独占获取，获取成功就返回；否则，进入同步队列；该方法会调用tryAcquire。
  
  * void acquireShared（）：共享获取
  
  同步队列：
    同步器用一个双向队列管理同步队列，当前线程获取失败，同步器会将当前线程和等待状态等信息构造为一个节点，加入同步队列，并且阻塞当前线程。当
    同步状态释放，会把第二个节点唤醒，使其尝试进入。
    
   在对队列的出入操作时，必须保证线程安全。同步器基于CAS实现了compareAndSetTail（）来进行入队操作。
   同步队列的首节点是获取同步状态成功的节点，其线程为null。首节点的线程执行完后，后继节点就将自己设置为首节点，并将内部线程交由执行。
   
### 5.2.1 独占式获取
    
    