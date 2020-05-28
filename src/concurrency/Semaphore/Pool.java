package concurrency.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/*
 信号量
 */
public class Pool<T>{
    private int size;
    private List<T> items=new ArrayList<>();
    private volatile boolean[] checkedOut;
    private Semaphore available;
    public Pool(Class<T> object, int size){
        this.size=size;
        checkedOut= new boolean[size];
        available=new Semaphore(size,true);//true,先入先出授权
        for(int i=0;i<size;i++){
            try{
                items.add(object.newInstance());
            }catch (Exception e){
                throw new RuntimeException();
            }
        }
    }
    public T checkOut() throws InterruptedException {
        available.acquire();//获得一个许可，如果没有，则阻塞;
        return getItem();
    }
    public void checkIn(T x){
        if(releaseItem(x)){
            available.release();
        }
    }
    public synchronized T getItem(){
        for(int i=0;i<size;i++){
            if(!checkedOut[i]){
                checkedOut[i]=true;
                return items.get(i);
            }
        }
        return null;
    }
    private synchronized boolean releaseItem(T item){
        int index=items.indexOf(item);
        if(index==-1){
            return false;
        }
        if(checkedOut[index]){
            checkedOut[index]=false;
            return true;
        }
        return false;
    }
}
