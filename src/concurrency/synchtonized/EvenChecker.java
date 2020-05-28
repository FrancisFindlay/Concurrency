package concurrency.synchtonized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements  Runnable{
    private IntGenerator generator;
    private final int id;
    public EvenChecker(IntGenerator g,int ident){
        this.generator=g;
        this.id=ident;
    }

    @Override
    public void run() {
        while(!generator.isCanceled()){
            int val=generator.next();
            if(val%2==0){
                System.out.println(val+" not even!");
                generator.cancel();
            }else{
                System.out.println(val+" failure!");
            }
        }
    }

    public static void test(IntGenerator gp,int count) {
        System.out.println("press control-c to exit!");
        ExecutorService exec=Executors.newCachedThreadPool();
        for(int i=0;i<count;i++){
            exec.execute(new EvenChecker(gp,i));
        }
    }

    public static void test(IntGenerator gp){
        test(gp,1000);
    }
}
