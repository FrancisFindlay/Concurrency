package concurrency.ReadWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.locks.LockSupport.park;

/*
 * 获得写锁后，读锁被阻塞
 */
public class Cache {
    static Map<String,Object> map=new HashMap<>();
    static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    static Lock writeLock=readWriteLock.writeLock();
    static Lock readLock=readWriteLock.readLock();
    public static final Object get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }
    public static final Object put(String key,Object value){
        writeLock.lock();
        try{
            return map.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }
    public static final void clear(){
        writeLock.lock();
        try{
            map.clear();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        Cache.map.put("1",1);
        Cache.map.put("2",2);
        Object o=Cache.map.put("2",3);
        System.out.println(o);
    }
}
