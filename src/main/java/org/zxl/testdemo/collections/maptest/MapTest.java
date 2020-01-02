package org.zxl.testdemo.collections.maptest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapTest {
    @Test
    public void test1(){
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        Set<Map.Entry<String, String>> me = map.entrySet();
        for(Map.Entry<String,String> mee:me){
            System.out.println(mee.getKey()+"::"+mee.getValue());
        }
    }


    @Test
    public void test2(){
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        //String key,value;
        map.forEach((key ,value) -> {
            System.out.println(key);
        });
    }

    @Test
    public void test3(){
        Map<String ,String> treeMap = new TreeMap<>();
        treeMap.put("1","1");
        treeMap.put("5","2");
        treeMap.put("3","3");
        treeMap.put("4","4");
        treeMap.put("2","5");
        treeMap.forEach((key ,value) -> {
            System.out.println(key);
        });
    }
}
