package concurrency.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue <T>{
    private Object[] items;
    private final int COUNT;
    private int currentNum=0;
    private int addIndex=0,removeIndex=0;
    private Lock lock=new ReentrantLock();
    private Condition notEmpty=lock.newCondition();
    private Condition notFull=lock.newCondition();

    public BoundedQueue(int num){
        items=new Object[num];
        COUNT=num;
    }

    public void add(T t){
        lock.lock();
        try{
            while(COUNT==currentNum){
                notFull.await();
            }
            items[addIndex]=t;
            if(++addIndex==COUNT){
                addIndex=0;
            }
            currentNum++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while(currentNum==0){
                notEmpty.await();
            }
            Object x=items[removeIndex];
            if(++removeIndex==COUNT)
                removeIndex=0;
            currentNum--;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

}
