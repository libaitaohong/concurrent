package concurrent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Created by zhanghe on 2017/2/26.
 * 流
 */
public class SDemo {
    public static void main(String[] args) {
        //流通常对一组数据相操作这组数据可能是一个集合，也有可能是数组
        List<String> ls = new ArrayList<>();
        ls.add("eee");
        ls.add("fff");
        ls.add("hello");
        //首先获取流，然后使用终端操作max，并自定义比较器来得到自己需要的最大值
        //终端操作返回的值类型是Optional类型，并且使用String的比较器来比较
        Optional<String> max = ls.stream().max(String::compareTo);
        System.out.println(max.get()); //输出流中最大值
        //sorted是中间操作，foreach是终端操作
        //首先获取集合流，然后进行排序得到中间流，再用foreach遍历输出每一个值
        ls.stream().sorted().forEach(e -> System.out.println(e));
        //这是找到流中不重复元素的数量
        ls.stream().distinct().count();
    }
}
