package concurrency.CountDownLatch;

import java.util.Random;
import java.util.concurrent.*;

class TaskPortion implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private static Random random =new Random(47);
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
        }catch (InterruptedException e){

        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this+"completed");
    }
    public String toString(){
        return String.format("%1$-3d",id);
    }
}

class WaitingTask implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private final CountDownLatch latch;
    WaitingTask(CountDownLatch latch){
        this.latch=latch;
    }
    public void run(){
        try{
            latch.await();
            System.out.println("latch barries passed for "+this);
        }catch (InterruptedException e){
            System.out.println(this+"interrupted");
        }
    }
    public String toString(){
        return String.format("WaitingTask %1$-3d",id);
    }
}


public class CountDownLatchDemo {
    static final int SIZE=100;

    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        CountDownLatch latch=new CountDownLatch(SIZE);
        for(int i=0;i<10;i++){
            exec.execute(new WaitingTask(latch));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }

        System.out.println("launched all tasks");
        for(int i=0;i<SIZE;i++){
            exec.execute(new TaskPortion(latch));
        }
        exec.shutdown();
    }
}
