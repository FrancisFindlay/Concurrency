package concurrency.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class a {
    class FizzBuzz {
    private int n;
    Semaphore semaphore=new Semaphore(1);
    int curNum=1;
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true){
            semaphore.acquire();
            try{
                if(curNum>n)
                    return ;
                if(curNum%5!=0&&curNum%3==0)
                    printFizz.run();
            }finally{
                curNum++;
                semaphore.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(true){
            semaphore.acquire();
            try{
                if(curNum>n)
                    return ;
                if(curNum%3!=0&&curNum%5==0)
                    printBuzz.run();
            }finally{
                curNum++;
                semaphore.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(true){
            semaphore.acquire();
            try{
                if(curNum>n)
                    return ;
                if(curNum%5==0&&curNum%3==0)
                    printFizzBuzz.run();
            }finally{
                curNum++;
                semaphore.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(true){
            semaphore.acquire();
            try{
                if(curNum>n)
                    return ;
                if(curNum%5!=0&&curNum%3!=0)
                    printNumber.accept(curNum);
            }finally{
                curNum++;
                semaphore.release();
            }
        }
    }
}
}
