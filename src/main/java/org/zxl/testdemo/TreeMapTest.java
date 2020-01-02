package org.zxl.testdemo;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    @Test
    public void test1(){
        Map<String,String> a = new TreeMap<String, String>();
        a.put("1","2323");
        a.put("2","233");
        System.out.println(a.get("1"));
    }
}
