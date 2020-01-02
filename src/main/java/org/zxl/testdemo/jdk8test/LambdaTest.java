package org.zxl.testdemo.jdk8test;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class LambdaTest {

    /**
     * treeSet HashSet都不能存储重复元素
     */
    @Test
    public void test1(){
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(4);
        System.out.println("treeSet.size():"+treeSet.size());
        for(Iterator iterator = treeSet.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }

        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.add(2);
        hashSet.add(1);
        hashSet.add(4);
        hashSet.add(3);
        for(Iterator iterator = hashSet.iterator(); iterator.hasNext();){
            System.out.println(iterator.next());
        }

    }

    /**
     * 匿名内部类实现
     */
    @Test
    public void test2(){
        System.out.println("Comparator接口实现");
        Comparator<Integer> cpt = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> set = new TreeSet<>(cpt);

        System.out.println("Runnable接口实现");
        new Thread( ()-> System.out.println("sdf")
        ).start();
    }


}
