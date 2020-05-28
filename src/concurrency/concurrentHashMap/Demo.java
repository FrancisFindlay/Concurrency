package concurrency.concurrentHashMap;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    static ConcurrentHashMap<Integer,Integer> map=new ConcurrentHashMap<>();
    static class testRun implements Runnable{
        int i=new Random().nextInt(100);
        @Override
        public void run() {
            map.put(i,1);
            System.out.println(map.get(i));
        }
    }
    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new testRun());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }
}
