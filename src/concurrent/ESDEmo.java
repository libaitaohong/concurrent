package concurrent;

import java.util.concurrent.*;

/**
 * Created by zhanghe on 2017/2/26..
 *Callable与Future
 */
public class ESDEmo {
    public static void main(String[] args) throws Exception{
        //带有固定线程池的执行器，如果要计算两个Callable，那就设置带有两个
        ExecutorService es = Executors.newFixedThreadPool(2); // 创建了带有两个线程池的执行器
        //把Callable提交到线程池中
        Future<Integer> r1 = es.submit(new MC(1,100)); // es.submit,把callable放到线程池中执行
        Future<Integer> r2 = es.submit(new MC(100,10000));
        //通过get()方法得到Callable的执行结果
        System.out.println(r1.get() + ":" + r2.get()); // get方法会抛出异常，在main方法后加上throws Exception
        es.shutdown();
    }
}
//泛型Integer表示执行结果的返回类型
class MC implements Callable<Integer>{
    private int begin, end;
    public MC(int begin, int end){
        this.begin = begin;
        this.end = end;
    }
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = begin; i < end; i++)
            sum += i;
        return sum;
    }
}