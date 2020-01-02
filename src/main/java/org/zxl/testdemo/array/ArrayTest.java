package org.zxl.testdemo.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest {
    @Test
    public void test1(){
        String [] str1 = new String[10];
        str1[0] = "a";
        str1[1] = "b";
        str1[2] = "c";
        str1[3] = "d";
        str1[4] = "e";

        String[] str2 = new String[10];
        System.arraycopy(str1 ,1 ,str2 ,0, 3);

        for (String dd : str2
             ) {
            System.out.println(dd);
        }
    }

    @Test
    public void test2(){
        List<String> str1 = new ArrayList<>();
        str1.add("a");
        str1.add("b");
        str1.add("c");
        str1.add("d");
        str1.add("e");

        List<String> str2 = new ArrayList<>();
        System.arraycopy(str1 ,1 ,str2 ,0, 3);

        for (String dd : str2
        ) {
            System.out.println(dd);
        }
    }
}
