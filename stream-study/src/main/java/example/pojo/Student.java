package example.pojo;

import java.util.Objects;

/**
 * @Author cpz
 * @Date 2020/11/1
 * @Description: 实体类
 */
public class Student {

    private String stuNo;

    private String stuName;

    private Integer stuAge;

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public Student(String stuNo, String stuName, Integer stuAge) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (stuNo != null ? !stuNo.equals(student.stuNo) : student.stuNo != null) return false;
        if (stuName != null ? !stuName.equals(student.stuName) : student.stuName != null) return false;
        return stuAge != null ? stuAge.equals(student.stuAge) : student.stuAge == null;
    }

    @Override
    public int hashCode() {
        int result = stuNo != null ? stuNo.hashCode() : 0;
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuAge != null ? stuAge.hashCode() : 0);
        return result;
    }
}
