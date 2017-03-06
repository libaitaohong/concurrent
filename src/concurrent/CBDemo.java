package concurrent;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * Created by zhanghe on 2017/2/26.
 * CyclicBarrier循环屏障：多个线程都到达预定点才可以继续执行
 * 模拟斗地主：凑够三个人游戏才能开始
 */
public class CBDemo {
    public static void main(String[] args) {
        //首先创建一个循环屏障，需要三个线程，3个线程凑齐后执行Runnable里面run方法输出GameStart
        //主线程一旦通过循环屏障就会执行某个动作，这个动作通过Runnable类实例来实现
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("Game start");
            }
        });
        new Player(cyclicBarrier, "A").start();
        new Player(cyclicBarrier, "B").start();
        new Player(cyclicBarrier, "C").start();
    }
}
class Player extends Thread{
    private  CyclicBarrier cyclicBarrier;
    public Player(CyclicBarrier cyclicBarrier, String name){
        setName(name);
        this.cyclicBarrier = cyclicBarrier;
    }
    public void run(){
        System.out.println(getName() + " is waiting other palyer...");
        try {
            //在循环屏障出等待
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}