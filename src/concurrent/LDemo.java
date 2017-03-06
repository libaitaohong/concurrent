package concurrent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Created by zhanghe on 2017/2/26.
 * Lock锁
 */
public class LDemo {
    public static void main(String[] args) {
        new MT().start();
        new MT().start();
        new MT().start();
        new MT().start();
    }
}
//数据类
class Data{
    static int i = 0;
    //static Lock lock = new ReentrantLock(); // 创建一个锁
    static AtomicInteger ai = new AtomicInteger(0);
    static void operate(){
        System.out.println(ai.incrementAndGet()); // s使用原子操作来代替锁的机制
        /*lock.lock();
        i++;
        System.out.println(i);
        lock.unlock();*/
    }
}
//线程类
class MT extends Thread{
    public void run(){
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Data.operate(); // 调用数据类的操作方法
        }
    }
}