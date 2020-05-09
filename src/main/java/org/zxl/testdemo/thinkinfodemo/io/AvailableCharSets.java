package org.zxl.testdemo.thinkinfodemo.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        while (it.hasNext()){
            String csName = it.next();
            System.out.printf(csName);
            Iterator aliases = charSets.get(csName).aliases().iterator();
            if(aliases.hasNext()){
                System.out.printf(": ");
            }
            while (aliases.hasNext()){
                System.out.printf(aliases.next().toString());
                if(aliases.hasNext()){
                    System.out.printf(", ");
                }
            }
            System.out.println();
        }
    }
}
