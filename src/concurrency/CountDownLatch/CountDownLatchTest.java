package concurrency.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/*
  如果初始化为0，await不会初始化。
  一个线程调用CountDown happens-before 于await之前。
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                c.countDown();
                System.out.println(c.getCount());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.countDown();
                System.out.println(c.getCount());
            }
        }).start();
        c.await();
        System.out.println("hahahaha");
    }
}
