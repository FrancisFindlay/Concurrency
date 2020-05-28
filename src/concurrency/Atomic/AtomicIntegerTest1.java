package concurrency.Atomic;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 原理:CAS 自旋锁 调用unsafe方法,提供了int，long，Object三种cas方法
 * 对于Boolean类型，把boolean转换为整形，再调用CAS
 */
public class AtomicIntegerTest1 {
    static AtomicInteger value=new java.util.concurrent.atomic.AtomicInteger(1);

    public static void main(String[] args) {
        //加上参数之后返回 6
        int value1=value.addAndGet(5);
        //当前value+1，返回原值 6
        int value2=value.getAndIncrement();
        //将value设置为newValue，返回old Value
        int value3=value.getAndSet(77);


    }
}
