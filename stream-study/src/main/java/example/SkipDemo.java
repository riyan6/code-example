package example;

import example.pojo.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author cpz
 * @Date 2020/11/1
 * @Description: 切片
 */
public class SkipDemo {

    /**
     * 指跳过Stream中存储的前n条数据(包含第n条数据)，返回后n条数据，如果n大于Stream中所有元素的个数，则返回空
     *
     * @param args
     */
    public static void main(String[] args) {
        // 新建数据对象
        Student s1 = new Student("1", "米小虎", 22);
        Student s2 = new Student("2", "米大虎", 25);
        Student s3 = new Student("3", "米废狗", 16);
        Student s4 = new Student("4", "米虎子", 18);
        Student s5 = new Student("5", "米无能", 10);
        // 添加数据
        List<Student> students = Arrays.asList(s1, s2, s3, s4, s5);
        // 创建 Stream
        Stream<Student> stream = students.stream();
        // 对 stream 过滤
        Stream<Student> s = stream.filter(item -> {
            // 过滤掉未成年人
            return item.getStuAge() >= 18;
        }).skip(2).distinct();
        // 输出
        s.forEach(item -> System.out.println(item));
    }

}


