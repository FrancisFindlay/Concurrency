# 1.读写状态的设计
    同样使用AQS去实现同步功能。
    读写锁将状态变量（int）分为高16位和16位。高16位表示读，低16位表示写。
    根据位运算进行读写状态的判断
        读:S>>>16
        写:S & 0x0000FFFF
# 2.写锁的获取和释放
    写锁是支持重入的拍他锁。
    如果当前写状态不为0，增加写状态。
    如果当前在获取写锁时，读锁已经被获取，当前线程进入等待状态。
    
# 3.读锁的获取和释放
    读锁是支持重入的共享锁。   
    读锁被获取，增加读状态。
    写锁被获取，读锁阻塞。
# 4.锁降级
    写锁降级位读锁。主要为了实现在读写不被其他线程干扰数据。
    不支持锁升级。
         