package org.zxl.testdemo.jdk8test.lambad;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortTest {
    List<String> names = Arrays.asList("peter","name","mike","xenia");

    @Test
    public void test1(){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String name:names){
            System.out.println(name);
        }
    }

    @Test
    public void test2(){
        Collections.sort(names, (String a, String b) ->{
            return a.compareTo(b);
        });
        for (String name:names){
            System.out.println(name);
        }
    }

}
