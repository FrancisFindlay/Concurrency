# Java并发框架和容器

# 6.1 ConcurrentHashMap

  HashMap在并发环境的问题：在put（）操作时，会形成死循环。
  
### 6.1.1 ConcurrentHashMap的属性
  * 底层：和HashMap一样，采用数组，链表以及红黑树。
  * 最大容量：2的30次方
  * 初始容量：16
  * 装填因子：0.75
  
### 6.1.2 ConcurrentHashMap在JDK1.7的实现
  采用segment+table实现的锁分段实现线程安全访问。
  
  Segment是一种可重入锁，继承了ReentrantLock，扮演锁的角色，HashEntry用来存储数值。
  一个map里面有一个Segment数组，一个Segment包含一个HashEntry数组，数组的每个元素是一个链表。当对HashEntry数组的数据进行修改，必须先
  获得对应的Segment锁。
  
![](https://hadyang.github.io/interview/docs/java/collection/Concurrenthashmap/images/ConcurrentHashMap.png)  
  
### 6.1.3 ConcurrentHashMao在JDK1.8的实现
  在JDK1.8，取消了Segment字段，直接采用transient volatile HashEntry[] table保存数据，采用table数组元素作为锁，进一步减少了并发冲突
  的可能。
  
  对于get（），通过volatile进行了实现。
  
# 6.2 ConcurrentLinkedQueue

# 6.3 Java中的阻塞队列
  BlockingQueue就是当插入或者移除时，会阻塞直到条件再次满足。
  
### 6.3.1 JDK提供了7个阻塞队列

  * ArrayBlockingQueue：有界阻塞队列，默认为非公平访问，也支持公平访问
  
  * LinkedBlockingQueue：链表结构的无界阻塞队列，默认容量为Integer.MAX_VALUE，可以显示声明
  
  * PriorityBlockingQueue：优先级排序的无界队列
  
  * DelayQueue：用优先级队列实现的无界阻塞队列
  
  * SynchronusQueue：不存储元素，put和get必须一一同步对应

  * LinkedtransferQueue：
  
  * LinkedBlockingDeque：
  
### 6.3.2 ArrayBlockingQueue
  使用ReentrantLock和两个Condition来实现，当队列已经满或者已经空，就先加入等待队列。
  
### 6.3.3 SynchronousQueue
  不存储元素，put操作必须等待一个take操作，否则不能继续添加。默认为非公平，支持公平。吞吐率高。    
       
        