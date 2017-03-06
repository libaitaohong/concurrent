package concurrent;
import java.util.concurrent.*;
/**
 * Created by zhanghe on 2017/2/27.
 * Fork/join框架
 * 求1-100000的和，分拆成两部分，1-50000,50001-100000
 */
public class FJDemo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Long> result = forkJoinPool.submit(new MTask(1,10));
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        forkJoinPool.shutdown();
    }
}
//RecursiveAction没有返回值，我们这里1-5W,5W01-10W分开计算需要返回值，就用RecursiveTask
class MTask extends RecursiveTask<Long>{
    static final int THRESHOLD = 5; //设置一个阀值
    //需要一个区间
    private int begin, end;
    public MTask(int begin, int end){
        this.begin = begin;
        this.end = end;
    }
    //实现方法compute，这个方法是任务执行完毕返回的值
    @Override
    protected Long compute() {
        long sum = 0;
        // 意思就是如果我的任务足够小就用一个处理器来执行
        if (end - begin <= THRESHOLD){
            for (int i = begin; i <= end; i++)
                sum +=i;
        }else{
            //如果任务不够小，就将一个任务拆分成两个子任务
            int mid = (begin + end) / 2;
            MTask left = new MTask(begin, mid);
            left.fork(); // 通过fork把任务剥离出来
            MTask right = new MTask(mid+1, end);
            right.fork();
            //左右计算结果合并
            Long lr = left.join(); // 通过join合并
            System.out.println(begin + "-" + mid + ":" + lr);
            Long rr = right.join();
            System.out.println(mid+1 + "-" + end + ":" + rr);
            sum = lr + rr;
        }
        return sum;
    }
}
