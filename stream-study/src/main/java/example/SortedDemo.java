package example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author cpz
 * @Date 2020/11/1
 * @Description: 自然排序
 */
public class SortedDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("c", "bbb", "abc", "bbbb");
        Stream<String> stream = list.stream();
        //即通过调用String方法中CompareTo，通过一个一个的比较字符的ASCLL值，首先比较首字符的ASCLL大小，相同的话再比较下一个
        stream = stream.sorted();
        stream.forEach(System.out::println);
    }

}
