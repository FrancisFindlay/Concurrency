package concurrency.Condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUseCase {
    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();

    public void conditionWait(){
        lock.lock();
        try{
            condition.await();
            System.out.println("await...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void ConditionSignal(){
        lock.lock();
        try {
            System.out.println("signal...");
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    static class Await implements Runnable{
        private ConditionUseCase conditionUseCase;
        public Await(ConditionUseCase conditionUseCase){
            this.conditionUseCase=conditionUseCase;
        }
        public void run(){
            conditionUseCase.conditionWait();
        }
    }
    static class Sign implements Runnable{
        private ConditionUseCase conditionUseCase;
        public Sign(ConditionUseCase conditionUseCase){
            this.conditionUseCase=conditionUseCase;
        }
        public void run(){
            conditionUseCase.ConditionSignal();
        }
    }
    public static void main(String[] args) {
        ConditionUseCase conditionUseCase=new ConditionUseCase();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new Await(conditionUseCase));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.execute(new Sign(conditionUseCase));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }
}
