package concurrency.Count;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count {
    private int count=0;
    private Random random=new Random(47);
    public synchronized int increment(){
        int temp=count;
        if((random.nextBoolean()))
            Thread.yield();
        return (count=++temp);
    }
    public synchronized int value(){return count;}
}

class Entrance implements Runnable{
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private int number=0;
    private final int id;
    private static volatile boolean canceled=false;
    public static void cancel() {canceled=true;}
    public Entrance(int id){
        this.id=id;
        entrances.add(this);
    }
    public void run(){
        while(!canceled){
            synchronized (this){
                ++number;
            }
            System.out.println(this+"Total: "+count.increment());
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch (InterruptedException e){
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("stopping"+this);
    }

    public synchronized int getValue(){
        return number;
    }
    public String toString(){
        return "Entrance"+id+":"+getValue();
    }
    public static int getTotalCount(){
        return count.value();
    }
    public static int sumEntrances(){
        int sum=0;
        for(Entrance entrance:entrances){
            sum+=entrance.getValue();
        }
        return sum;
    }
}

public class OrnamentalGarden{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        /*
        主线程要求线程池关闭，但不会为此等待线程池执行完毕
        shutdown() 作为函数，当然是立即执行，也即是不再接受新任务了；
        但是它既不会强行终止正在执行的任务，也不会取消已经提交的任务。
        也就是说之前提交的5个任务，仍然会执行完毕，且跟主线程生命周期无关，也就是即便你直接在后面写上：
         if (1==1) return;
         来立即结束主函数，你也会发现线程池的5个任务会顺利执行完毕。
         */
        if(!exec.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("Some tasks were not terminated");
        }
        System.out.println("Total:"+Entrance.getTotalCount());
        System.out.println("Sum of Entrances:"+Entrance.sumEntrances());
    }
}
