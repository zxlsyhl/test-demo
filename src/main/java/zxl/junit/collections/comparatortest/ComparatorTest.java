package zxl.junit.collections.comparatortest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    @Test
    public void test1(){
        Student stu1 = new Student(1,"11",2);
        Student stu2 = new Student(2,"11",1);
        Student stu3 = new Student(3,"11",1);
        Student stu4 = new Student(4,"11",2);
        List<Student> stuList = new ArrayList<>();
        stuList.add(stu1);
        stuList.add(stu2);
        stuList.add(stu3);
        stuList.add(stu4);
        stuList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge()>=o2.getAge()?1:-1;
            }
        });

        for (Student stu:stuList) {
            System.out.println(stu.getId());
        }
    }
}
