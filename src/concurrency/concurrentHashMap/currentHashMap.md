# 1.HashMap存在的问题
    在并发执行put时会产生循环链表。
    
# 2.ConcurrentHashMap的结构    
    
    由Segment[]和HashEntry[]组成。
    Segment是ReentrantLock，扮演锁的角色。Entry存储键值对。segment的长度必须是2的n次方。
    一个ConcurrentHashMap包含一个Segment数组，一个Segment包含一个HashEntry数组。每个HashEntry是链表结构。
    当对HashEntry修改，必须先获得它的Segment锁。
# 3.初始化
    1.initialCapacity:总容量，默认16
    2.concurrencyLevel:最大为65535，意味这Segments数组最大长度为65536，默认为16

# 4.定位Segment
    使用分段锁进行保护不同段的数据，因此必须先定位Segment
    通过segmentFor（int hash）来定位，hash通过特定算法进行再散列
    
# 5.get
    1.通过hash（hashcode）再散列，再通过segmentFor定位segment
    2.使用volatile修饰共享变量解锁
    3.定位segment和定位entry使用的散列算法一样，都是与数组长度-1 去与，但是定位segment使用再散列得到的值的高位，定位entry直接使用再
        散列的值。目的是为了避免两次散列的值一样，导致元素再segment散开，但是再entry里没有散列开
            
# 6.put
    put首先定位segment，然后在segment里面插入。插入时首先判断是否扩容，然后定位位置。扩容时，只对特定的segment进行扩容为原来的二倍
    
    
# 7.size
    为了统计所有元素的数量，volatile的count，在统计时，用到了一个modcount变量，在put，remove等方法操作前都会将modcount加1。            