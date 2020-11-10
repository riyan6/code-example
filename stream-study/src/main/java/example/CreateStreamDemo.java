package example;

import java.util.Arrays;
import java.util.List;

/**
 * @Author cpz
 * @Date 2020/11/1
 * @Description: 创建Stream步骤
 */
public class CreateStreamDemo {

    public static void main(String[] args) {

        // 第一种方式：通过集合中的stream()方法创建Stream
        List<String> list = Arrays.asList("红太狼", "灰太狼", "喜羊羊");
        // Stream<String> stream = list.stream();

        // 通过集合中的parallelStream方法创建
        // Stream<String> stream2 = list.parallelStream();

        // 第二种方式：通过java.util.Arrays下的静态方法stream创建Stream
        Integer integerArr[] = new Integer[]{1, 0, 2, 4};
        // 这里需要注意的是Arrays中的 stream方法里面的参数需要一个数组，且数组的类型是一个引用类型或者是一个包装类
        // Stream<Integer> stream3 = Arrays.stream(integerArr);

        // 第三种方式：通过Stream中的of方法,实际上这种方式创建Stream实际上间接的通过调用Arrays中的stream()静态方法
        // Stream<String> stream4 = Stream.of("a", "b", "c");

    }

}
