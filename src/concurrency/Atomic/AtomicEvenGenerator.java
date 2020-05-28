package concurrency.Atomic;

import concurrency.synchtonized.EvenChecker;
import concurrency.synchtonized.IntGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger currentEvenValue=new AtomicInteger(0);
    public int next(){
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());

    }
}
