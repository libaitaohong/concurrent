package concurrent;

import java.util.concurrent.Phaser;

/**
 * Created by zhanghe on 2017/2/26.
 * Phaser同步器
 * 模拟饭店服务的过程,传菜的，厨师，上菜的
 */
public class PDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        System.out.println("starting...");
        new Worker(phaser, "services").start();
        new Worker(phaser, "cooker").start();
        new Worker(phaser, "food services").start();

        //这个for循环的意思是有3个订单，对于每个订单所有人处理完毕之后才能继续执行输出语句
        for (int i = 1; i <= 3; i++){
            phaser.arriveAndAwaitAdvance();
            System.out.println("order" + i + " finished");
        }

        phaser.arriveAndDeregister();
        System.out.println("all done");
    }
}
class Worker extends Thread{
    private Phaser phaser;
    public Worker(Phaser phaser, String name){
        this.setName(name);
        this.phaser = phaser;
        phaser.register(); // 把当前线程注册到Phaser里面去
    }
    public void run(){
        for (int i = 1; i <= 4; i++){
            System.out.println("current order is:" + i + ":" + getName());
            if (i == 3)
                phaser.arriveAndDeregister(); // 所有订单处理完毕后注销
            else
                phaser.arriveAndAwaitAdvance(); //如果没有处理完那就等待其他订单处理完毕
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
