package concurrent;
import java.util.concurrent.Semaphore;
/**
 * Created by zhanghe on 2017/2/26.
 * 信号量同步器Semaphore
 * 模拟银行柜台服务，2个柜台，3个客户。。
 */
public class SeDemo {
    public static void main(String[] args){
        //创建一个信号量，最多有几个许可证，这里是两个，意思就是最多允许几个并发线程同时运行
        Semaphore semaphore = new Semaphore(2);

        Person p1 = new Person(semaphore, "A");
        p1.start();
        Person p2 = new Person(semaphore, "B");
        p2.start();
        Person p3 = new Person(semaphore, "C");
        p3.start();
    }
}
class Person extends Thread{
    private Semaphore semaphore;
    public Person(Semaphore semaphore, String name){
        setName(name);
        this.semaphore = semaphore;
    }
    public void run(){
        System.out.println(getName() + " is waiting..."); //人来了，在等待许可证
        try {
            semaphore.acquire(); //获取信号量的许可证，可以获取一个或多个许可证。这里需要抛出异常
            System.out.println(getName() + " is servicing..."); //获得许可，正在服务
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " is done..."); //服务结束，释放许可证
        semaphore.release();
    }
}