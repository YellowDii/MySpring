package nju.java.pattern.template_method_pattern.course;

public class NetworkCourseTest {
    public static void main(String[] args) {
        System.out.println("---java架构师课程---");
        NetworkCourse javaCourse=new JavaCourse();
        javaCourse.createCourse();

        System.out.println("---大数据课程---");
        NetworkCourse bigDataCourse=new BigDataCourse(true);
        bigDataCourse.createCourse();
    }
}
