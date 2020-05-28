package concurrency.Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/*
 *  不会改变原来数组的值，会重新复制一份。
 */
public class AtomicArray {
    static int []value=new int[]{1,2};
    static AtomicIntegerArray array=new AtomicIntegerArray(value);

    public static void main(String[] args) {
        array.getAndSet(1,3);
        System.out.println(array.get(1));
        System.out.println(array.get(0));
        System.out.println(value[1]);
    }
}
