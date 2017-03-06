package concurrent;

import java.util.concurrent.Exchanger;
/**
 * Created by zhanghe on 2017/2/26.
 * Exchanger
 * 模拟一个简单的对话的例子
 */
public class EDemo {
    public static void main(String[] args) {
        Exchanger<String> ex = new Exchanger<>();
        new A(ex).start();
        new B(ex).start();
    }
}
class A extends Thread{
    private Exchanger<String> ex;
    public A(Exchanger<String> ex){
        this.ex = ex;
    }
    public void run(){
        String str = null;
        try {
            str = ex.exchange("A:Hello");
            System.out.println(str);
            str = ex.exchange("A:1");
            System.out.println(str);
            str = ex.exchange("A:2");
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class B extends Thread{
    private Exchanger<String> ex;
    public B(Exchanger<String> ex){
        this.ex = ex;
    }
    public void run(){
        String str = null;
        try {
            str = ex.exchange("B:Hi");
            System.out.println(str);
            str = ex.exchange("B:1");
            System.out.println(str);
            str = ex.exchange("B:2");
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}