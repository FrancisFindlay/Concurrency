package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
        //异常不能跨线程回到main，因此必须在run（）捕获
    }

    public static void main(String[] args) {
        try{
            ExecutorService exec=Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        }catch (RuntimeException e){
            System.out.println(e);
            System.out.println("has been caught!");
        }
    }
}
