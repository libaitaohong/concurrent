package concurrent;
import java.util.concurrent.CountDownLatch;
/**
 * Created by zhanghe on 2017/2/26.
 * 技术栓：CountDownLatch
 * 模拟一个赛跑比赛的例子，3 2 1后所有运动员才一起跑
 */
public class CDDemo {
    public static void main(String[] args) {
        //创建一个技术栓，需要多少个事件发生才能让线程继续执行
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Racer(countDownLatch,"A").start();
        new Racer(countDownLatch,"B").start();
        new Racer(countDownLatch,"C").start();
        //这里的i就是第几个事件，这里对技术栓进行一个倒计时
        for (int i = 0; i < 3; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(3 - i);
            //当这里计数为0的时候，所有的线程就并发执行了
            countDownLatch.countDown();
            if (i == 2)
                System.out.println("start");
        }
    }
}
class Racer extends Thread{
    private CountDownLatch countDownLatch;
    public Racer(CountDownLatch countDownLatch, String name){
        setName(name);
        this.countDownLatch = countDownLatch;
    }
    public void run(){
        try {
            countDownLatch.await();
            for(int i = 0; i < 3; i++ ){
                System.out.println(getName() + " i=" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}